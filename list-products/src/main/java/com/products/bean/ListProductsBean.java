package com.products.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import com.products.model.Product;
import com.products.service.ProductService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named(value = "productBean")
@SessionScoped
public class ListProductsBean implements Serializable {

	@Inject
	private ProductService productService;
	@Inject
	private SessionBean sessionBean;

	// backing bean fields extracted from model
	List<Product> listProduct = new ArrayList<Product>();
	private Long id;
	private java.lang.String productName;
	private java.lang.String productCode;
	private java.lang.Long availableQty;

	// -----------------------------------------------
	@PostConstruct
	public void init() {
		setListProduct(getProductService().getAllProducts());

	}
	// -----------------------------------------------

	// -----------------------------------------------
	public void createOrEditItem(ActionEvent evt) {
		Product product;
		if (getSessionBean().getCurrentMode().equals(SessionBean.MODE_INSERT)) {
			product = new Product();

		} else {
			product = getProductService().getProductById(Long.valueOf(sessionBean.getCurrentRowId())).get();
		}

		product.setProductName(productName);
		product.setProductCode(productCode);
		product.setAvailableQty(availableQty);

		getProductService().saveProduct(product);
		PrimeFaces.current().executeScript("PF('w-dlg-add-edit-item').hide();");
		SessionBean.displayMessage("msgs", FacesMessage.SEVERITY_INFO, "information", "done");
		setListProduct(getProductService().getAllProducts());
	}

	// ---
	public void cancelModifs(ActionEvent evt) {
		PrimeFaces.current().resetInputs(new String[] { "frm-add-edit-item:p-add-edit-item" });
		SessionBean.displayMessage("msgs", FacesMessage.SEVERITY_WARN, "warning", "updateCanceled");
	}

	// ---
	public void prepareAddEditDialog() {
		if (getSessionBean().getCurrentMode().equals(SessionBean.MODE_EDIT)) {
			Product product = getProductService().getProductById(getSessionBean().getCurrentRowId()).get();
			this.setProductName(product.getProductName());
			this.setProductCode(product.getProductCode());
			this.setAvailableQty(product.getAvailableQty());
			this.setId(product.getId());

		} else {
			this.setProductName(null);
			this.setProductCode(null);
			this.setAvailableQty(null);
			this.setId(null);

		}
	}

	// ---
	public void deleteItem(ActionEvent evt) {
		Product product = getProductService().getProductById(getId()).get();
		if (product != null) {
			getProductService().deleteProduct(product);
			setListProduct(getProductService().getAllProducts());
		}
		SessionBean.displayMessage("msgs", FacesMessage.SEVERITY_INFO, "information", "done");
	}
	// -----------------------------------------------

	// -----------------------------------------------
	public ProductService getProductService() {
		return this.productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// ---
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	// ---
	public java.lang.String getProductName() {
		return this.productName;
	}

	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}

	public java.lang.String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(java.lang.String productCode) {
		this.productCode = productCode;
	}

	public java.lang.Long getAvailableQty() {
		return this.availableQty;
	}

	public void setAvailableQty(java.lang.Long availableQty) {
		this.availableQty = availableQty;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getListProduct() {
		return this.listProduct;
	}

	public void setListProduct(List<Product> list) {
		this.listProduct = list;
	}

}
