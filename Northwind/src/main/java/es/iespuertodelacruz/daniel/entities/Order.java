package es.iespuertodelacruz.daniel.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderID;

	private double freight;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date requiredDate;

	private String shipAddress;

	private String shipCity;

	private String shipCountry;

	private String shipName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date shippedDate;

	private String shipPostalCode;

	private String shipRegion;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="order", cascade=CascadeType.PERSIST)
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CustomerID")
	private Customer customer;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="EmployeeID")
	private Employee employee;

	//bi-directional many-to-one association to Shipper
	@ManyToOne
	@JoinColumn(name="ShipVia")
	private Shipper shipper;

	public Order() {
	}

	public int getOrderID() {
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public double getFreight() {
		return this.freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return this.requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getShipAddress() {
		return this.shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCity() {
		return this.shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipCountry() {
		return this.shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public String getShipName() {
		return this.shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public Date getShippedDate() {
		return this.shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getShipPostalCode() {
		return this.shipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}

	public String getShipRegion() {
		return this.shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);

		return orderDetail;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Shipper getShipper() {
		return this.shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

}