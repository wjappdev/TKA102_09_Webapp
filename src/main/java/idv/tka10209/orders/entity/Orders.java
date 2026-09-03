package idv.tka10209.orders.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orders_id", updatable = false)
	private Integer orderId;
	
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name = "shipping_address", length = 255)
    private String shippingAddress;

    @Column(name = "payment_method", length = 20)
    private String paymentMethod;

    @Column(name = "product_total")
    private Integer productTotal;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "shopping_credit")
    private Integer shoppingCredit;

    @Column(name = "actual_payment_amount")
    private Integer actualPaymentAmount;

    @Column(name = "receiver_name", length = 20)
    private String receiverName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "logistics_note", length = 500)
    private String logisticsNote;

    @Column(name = "orders_note", length = 500)
    private String ordersNote;

    @Column(name = "orders_date")
    private LocalDateTime ordersDate;

    @Column(name = "orders_status")
    private Byte ordersStatus;

    @Column(name = "employee_id")
    private Integer employeeId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Integer productTotal) {
		this.productTotal = productTotal;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Integer shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Integer getShoppingCredit() {
		return shoppingCredit;
	}

	public void setShoppingCredit(Integer shoppingCredit) {
		this.shoppingCredit = shoppingCredit;
	}

	public Integer getActualPaymentAmount() {
		return actualPaymentAmount;
	}

	public void setActualPaymentAmount(Integer actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLogisticsNote() {
		return logisticsNote;
	}

	public void setLogisticsNote(String logisticsNote) {
		this.logisticsNote = logisticsNote;
	}

	public String getOrdersNote() {
		return ordersNote;
	}

	public void setOrdersNote(String ordersNote) {
		this.ordersNote = ordersNote;
	}

	public LocalDateTime getOrdersDate() {
		return ordersDate;
	}

	public void setOrdersDate(LocalDateTime ordersDate) {
		this.ordersDate = ordersDate;
	}

	public Byte getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(Byte ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
