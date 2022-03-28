class Product {
  Product({
    this.productName='',
    this.unitPrice=0.0,
    this.discount=0.0,
    this.quantity=0.0,
    this.unitOfMeasure=0.0,
    this.status='',
    this.tax=0.0,
  });

  String productName;
  double unitPrice;
  double discount;
  double quantity;
  double unitOfMeasure;
  String status;
  double tax;

  factory Product.fromJson(Map<String, dynamic> json) =>
      Product(
        productName: json["product_name"] == null ? null : json["product_name"],
        unitPrice: json["unit_price"] == null ? null : json["unit_price"],
        discount: json["discount"] == null ? null : json["discount"],
        quantity: json["quantity"] == null ? null : json["quantity"],
        unitOfMeasure: json["unit_of_measure"] == null
            ? null
            : json["unit_of_measure"],
        status: json["status"] == null ? null : json["status"],
        tax: json["tax"] == null ? null : json["tax"].toDouble(),
      );

  Map<String, dynamic> toJson() =>
      {
        "product_name": productName,
        "unit_price": unitPrice,
        "discount": discount,
        "quantity": quantity,
        "unit_of_measure": unitOfMeasure,
        "status": status,
        "tax": tax
      };
}


