package es.basket.rmadrid.dao.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "menus")
@EntityListeners(AuditingEntityListener.class)
public class Menus extends BaseEntity {

	private String item;

	private String link;

	@Column(name = "item_order")
	private long itemOrder;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(long itemOrder) {
		this.itemOrder = itemOrder;
	}
}
