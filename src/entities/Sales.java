package entities;

import java.util.Date;

public class Sales {
  private Integer saleNumber;
  private String clientName;
  private Date dateOfSale;
  private Double saleValue;

  public Sales(Integer saleNumber, String clientName, Date dateOfSale, Double saleValue) {
    this.saleNumber = saleNumber;
    this.clientName = clientName;
    this.dateOfSale = dateOfSale;
    this.saleValue = saleValue;
  }

  public Integer getSaleNumber() {
    return saleNumber;
  }

  public void setSaleNumber(Integer saleNumber) {
    this.saleNumber = saleNumber;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Date getDateOfSale() {
    return dateOfSale;
  }

  public void setDateOfSale(Date dateOfSale) {
    this.dateOfSale = dateOfSale;
  }

  public Double getSaleValue() {
    return saleValue;
  }

  public void setSaleValue(Double saleValue) {
    this.saleValue = saleValue;
  }

  @Override
  public String toString() {
    return "Número da venda = " + saleNumber +
            "' Nome do cliente = '" + clientName + '\'' +
            ", Data da venda = " + dateOfSale +
            ", Valor da venda = " + saleValue;
  }
}