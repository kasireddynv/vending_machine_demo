package com.java.vendingmachine.logic.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.vendingmachine.common.ErrorMsgConsts;
import com.java.vendingmachine.common.Product;
import com.java.vendingmachine.common.State;
import com.java.vendingmachine.common.VendingMachineStates;
import com.java.vendingmachine.database.entity.Inventory;
import com.java.vendingmachine.database.repository.InventoryRepository;
import com.java.vendingmachine.errors.InternalServerError;
import com.java.vendingmachine.errors.NoQuarterException;
import com.java.vendingmachine.errors.NoSufficientQuarterException;
import com.java.vendingmachine.errors.SoldOutException;
import com.java.vendingmachine.logic.api.VendingMachineService;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public void insertQuarter(int quarter) {

		if (quarter < 1) {

			State.getInstance().setState(VendingMachineStates.NO_QUARTER);

			throw new NoSufficientQuarterException(ErrorMsgConsts.NO_VALID_FUNDS);

		} else if (quarter > 1) {

			State.getInstance().setState(VendingMachineStates.NO_QUARTER);

			throw new NoSufficientQuarterException(ErrorMsgConsts.NO_VALID_FUNDS);
		}

		State.getInstance().setState(VendingMachineStates.HAS_QUARTER);

	}

	@Override
	public void removeQuarter() {

		State.getInstance().setState(VendingMachineStates.NO_QUARTER);
	}

	@Override
	public void pushSodaButton() {

		try {

			if (!VendingMachineStates.HAS_QUARTER.equals(State.getInstance().getState())) {

				State.getInstance().setState(VendingMachineStates.NO_QUARTER);

				throw new NoQuarterException("Please insert quarter to dispense SODA");
			}

			State.getInstance().setState(VendingMachineStates.DISPENSE);

			dispenseSoda();

		} catch (Exception e) {

			State.getInstance().setState(VendingMachineStates.NO_QUARTER);

			throw new InternalServerError(e.getMessage());
		}

	}

	@Override
	public void dispenseSoda() {

		try {

			if (!VendingMachineStates.DISPENSE.equals(State.getInstance().getState())) {

				State.getInstance().setState(VendingMachineStates.NO_QUARTER);

				throw new NoQuarterException("Please insert quarter to dispense SODA");
			}

			Optional<Inventory> inventoryEntity = this.inventoryRepository.findById(Product.SODA.name());

			if (inventoryEntity.isPresent() && inventoryEntity.get().getQuantity() > 0) {

				Inventory entity = inventoryEntity.get();

				entity.setQuantity(entity.getQuantity() - 1);

				State.getInstance().setState(VendingMachineStates.NO_QUARTER);

				this.inventoryRepository.save(entity);

			} else {

				State.getInstance().setState(VendingMachineStates.NO_QUARTER);

				throw new SoldOutException(ErrorMsgConsts.SODA_OUT_OF_STOCK);
			}
		} catch (Exception e) {

			State.getInstance().setState(VendingMachineStates.NO_QUARTER);

			if (e instanceof SoldOutException) {
				throw new SoldOutException(ErrorMsgConsts.SODA_OUT_OF_STOCK);
			}

			throw new InternalServerError(e.getMessage());
		}
	}

}
