package com.red.exercise.utils;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewCreator
{
		//To avoid having the same lines of code for creating a Model and View and configuring it
		public static ModelAndView createModelAndView (String objectName, Object objectToPass, String view)
		{
			ModelAndView mv = new ModelAndView();
			mv.addObject(objectName, objectToPass);
			mv.setViewName(view);
			return mv;
		}
}
