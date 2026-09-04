package idv.tka10209.orders.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "member")
public class Member {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "member_name", length = 50)
    private String memberName;

    @Column(name = "member_birthday")
    private LocalDate memberBirthday;

    @Column(name = "member_account", length = 50)
    private String memberAccount;

    @Column(name = "member_email", length = 100)
    private String memberEmail;

    @Column(name = "member_password", length = 255)
    private String memberPassword;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "member_status")
    private Byte memberStatus;

    @Column(name = "shopping_credit")
    private Integer shoppingCredit;


    // ===== Constructor =====

    public Member() {
    }


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public LocalDate getMemberBirthday() {
		return memberBirthday;
	}


	public void setMemberBirthday(LocalDate memberBirthday) {
		this.memberBirthday = memberBirthday;
	}


	public String getMemberAccount() {
		return memberAccount;
	}


	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberPassword() {
		return memberPassword;
	}


	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Byte getMemberStatus() {
		return memberStatus;
	}


	public void setMemberStatus(Byte memberStatus) {
		this.memberStatus = memberStatus;
	}


	public Integer getShoppingCredit() {
		return shoppingCredit;
	}


	public void setShoppingCredit(Integer shoppingCredit) {
		this.shoppingCredit = shoppingCredit;
	}

}
