package es.basket.rmadrid.utils;

import java.util.List;

import org.springframework.ui.Model;

import es.basket.rmadrid.dao.entity.SiteConfiguration;

public class SiteConfigurationUtils {

	public static void addConfigurationToModel(List<SiteConfiguration> configuration, Model model, int prefixSize) {
		for (SiteConfiguration conf : configuration) {
			model.addAttribute(conf.getName().substring(prefixSize), conf.getValue());
		}
	}
}
