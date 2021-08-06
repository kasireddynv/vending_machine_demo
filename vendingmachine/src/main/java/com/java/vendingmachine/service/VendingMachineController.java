package com.java.vendingmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.vendingmachine.common.ErrorMsgConsts;
import com.java.vendingmachine.errors.NoSufficientQuarterException;
import com.java.vendingmachine.logic.api.VendingMachineService;
import com.java.vendingmachine.model.Coin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VendingMachineController {

	@Autowired
	VendingMachineService vendingMachineService;

	@GetMapping("/insertQuarter")
	public ModelAndView insertQuarter(Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("coin", new Coin());

		modelAndView.setViewName("insertquarter");

		return modelAndView;
	}

	@GetMapping("/error")
	public ModelAndView error(Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", new Error());

		modelAndView.setViewName("error");

		return modelAndView;
	}

	@RequestMapping(value = "/insertQuarter", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("coin") Coin coin) {

		log.info("**** insertQuarter ****: {}", coin.getQuarter());

		ModelAndView modelAndView = new ModelAndView();

		try {

			vendingMachineService.insertQuarter(coin.getQuarter());

			modelAndView.setViewName("item");

		} catch (NoSufficientQuarterException e) {

			log.info("Error: {}", e.getMessage());

			if (ErrorMsgConsts.NO_SUFFICIENT_FUNDS.equals(e.getMessage())) {

				modelAndView.setViewName("noSufficientFundsError");

			} else if (ErrorMsgConsts.NO_VALID_FUNDS.equals(e.getMessage())) {

				modelAndView.setViewName("noValidFundsError");

			} else {
				modelAndView.setViewName("error");
			}

		} catch (Exception e) {

			log.info("Error: {}", e.getMessage());

			modelAndView.setViewName("error");

			modelAndView.addObject("errorMsg", e.getMessage());

		}

		return modelAndView;
	}

	@GetMapping("/removequarter")
	public ModelAndView removeQuarter(Model model) {

		log.info("**** removeQuarter ****");

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("removequarter");

		return modelAndView;
	}

	@RequestMapping(value = "/removeQuarter", method = RequestMethod.POST)
	public ModelAndView remove(@ModelAttribute("coin") Coin coin) {

		log.info("**** removeQuarter ****: {}", coin.getQuarter());

		vendingMachineService.removeQuarter();

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("removeQuarterSuccess");

		return modelAndView;
	}

	@RequestMapping(value = "/dispenseSoda", method = RequestMethod.POST)
	public ModelAndView dispenseSoda(Model model) {

		log.info("**** dispenseSoda ****");

		ModelAndView modelAndView = new ModelAndView();

		try {

			vendingMachineService.pushSodaButton();
			modelAndView.setViewName("success");

		} catch (Exception e) {

			if (ErrorMsgConsts.SODA_OUT_OF_STOCK.equals(e.getMessage())) {

				modelAndView.setViewName("noSodaError");

			} else {
				modelAndView.setViewName("error");
			}

		}

		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView home(Model model) {

		log.info("**** Go To Home ****");

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("index");

		return modelAndView;
	}

}
