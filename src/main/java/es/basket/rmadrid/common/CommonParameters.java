package es.basket.rmadrid.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import es.basket.rmadrid.dao.entity.SiteConfiguration;
import es.basket.rmadrid.dao.repository.MenusRepository;
import es.basket.rmadrid.dao.repository.SiteConfigurationRepository;
import es.basket.rmadrid.util.SiteConfigurationUtils;

@Component
public class CommonParameters {

	@Autowired
	private SiteConfigurationRepository siteConfiguration;
	
	@Autowired
	private MenusRepository menus;
	
	public void get(Model model) {
		List<SiteConfiguration> configuration = siteConfiguration.findByPrefix("admin");

		SiteConfigurationUtils.addConfigurationToModel(configuration, model, 6);
		
		model.addAttribute("menu", menus.findMenu("admin-menu"));
	}
}
