
enum PaymentStatus {
  COMPLETED,
  AUTHORIZED,
  VOIDED,
  REFUNDED,
  CANCELED,
  FAILED,
  HOLD,
  DECLINED,
  READ_CARD_ONLY}

class Payment{

  Payment({
    this.adjustToAddCharges,
    this.amount,
    this.applicationIndex,
    this.authzOnly,
    this.autoClose,
    this.autoPrintReceipt,
    this.callerPackageName,
    this.captureWithCard,
    this.cashOnly,
    this.cashbackAmount,
    this.creditOnly,
    this.currency,
    this.debitOnly,
    this.disableCash,
    this.disableChangeAmount,
    this.disableCheck,
    this.disableDebit,
    this.disableDebitCards,
    this.disableEMVCL,
    this.disableEMVCT,
    this.disableEbtCashBenefits,
    this.disableEbtFoodStamps,
    this.disableEbtVoucher,
    this.disableMSR,
    this.disableManual,
    this.disableOther,
    this.disablePaymentOptions,
    this.disablePaymentWallet,
    this.disableTip,
    this.ebtCashCashback,
    this.incrementalAuthWithCard,
    this.isBalanceInquiry,
    this.manualEntry,
    this.multiTender,
    this.nonReferencedCredit,
    this.notes,
    this.offlineAuth,
    this.order,
    this.orderId,
    this.readCardDataOnly,
    this.referenceId,
    this.skipPaymentConfirmationScreen,
    this.skipReceiptScreen,
    this.skipSignatureScreen,
    this.status,
    this.tipAmount,
    this.transactions,
    this.voucher,
  });

  bool? adjustToAddCharges;
  int? amount;
  int? applicationIndex;
  bool? authzOnly;
  bool? autoClose;
  bool? autoPrintReceipt;
  String? callerPackageName;
  bool? captureWithCard;
  bool? cashOnly;
  int? cashbackAmount;
  bool? creditOnly;
  String? currency;
  bool? debitOnly;
  bool? disableCash;
  bool? disableChangeAmount;
  bool? disableCheck;
  bool? disableDebit;
  bool? disableDebitCards;
  bool? disableEMVCL;
  bool? disableEMVCT;
  bool? disableEbtCashBenefits;
  bool? disableEbtFoodStamps;
  bool? disableEbtVoucher;
  bool? disableMSR;
  bool? disableManual;
  bool? disableOther;
  bool? disablePaymentOptions;
  bool? disablePaymentWallet;
  bool? disableTip;
  bool? ebtCashCashback;
  bool? incrementalAuthWithCard;
  bool? isBalanceInquiry;
  bool? manualEntry;
  bool? multiTender;
  bool? nonReferencedCredit;
  String? notes;
  bool? offlineAuth;
  Order? order;
  String? orderId;
  bool? readCardDataOnly;
  String? referenceId;
  bool? skipPaymentConfirmationScreen;
  bool? skipReceiptScreen;
  bool? skipSignatureScreen;
  PaymentStatus? status;
  int? tipAmount;
  List<Transaction>? transactions;
  bool? voucher;


  factory Payment.fromJson(Map<String, dynamic> json) => Payment(
    // adjustToAddCharges: json["adjustToAddCharges"] == null ? null : json["adjustToAddCharges"],
    amount: json["amount"] == null ? null : json["amount"],
    applicationIndex: json["applicationIndex"] == null ? null : json["applicationIndex"],
    authzOnly: json["authzOnly"] == null ? null : json["authzOnly"],
    autoClose: json["autoClose"] == null ? null : json["autoClose"],
    autoPrintReceipt: json["autoPrintReceipt"] == null ? null : json["autoPrintReceipt"],
    callerPackageName: json["callerPackageName"] == null ? null : json["callerPackageName"],
    captureWithCard: json["captureWithCard"] == null ? null : json["captureWithCard"],
    cashOnly: json["cashOnly"] == null ? null : json["cashOnly"],
    cashbackAmount: json["cashbackAmount"] == null ? null : json["cashbackAmount"],
    creditOnly: json["creditOnly"] == null ? null : json["creditOnly"],
    currency: json["currency"] == null ? null : json["currency"],
    debitOnly: json["debitOnly"] == null ? null : json["debitOnly"],
    disableCash: json["disableCash"] == null ? null : json["disableCash"],
    disableChangeAmount: json["disableChangeAmount"] == null ? null : json["disableChangeAmount"],
    disableCheck: json["disableCheck"] == null ? null : json["disableCheck"],
    disableDebit: json["disableDebit"] == null ? null : json["disableDebit"],
    disableDebitCards: json["disableDebitCards"] == null ? null : json["disableDebitCards"],
    disableEMVCL: json["disableEMVCL"] == null ? null : json["disableEMVCL"],
    disableEMVCT: json["disableEMVCT"] == null ? null : json["disableEMVCT"],
    disableEbtCashBenefits: json["disableEbtCashBenefits"] == null ? null : json["disableEbtCashBenefits"],
    disableEbtFoodStamps: json["disableEbtFoodStamps"] == null ? null : json["disableEbtFoodStamps"],
    disableEbtVoucher: json["disableEbtVoucher"] == null ? null : json["disableEbtVoucher"],
    disableMSR: json["disableMSR"] == null ? null : json["disableMSR"],
    disableManual: json["disableManual"] == null ? null : json["disableManual"],
    disableOther: json["disableOther"] == null ? null : json["disableOther"],
    disablePaymentOptions: json["disablePaymentOptions"] == null ? null : json["disablePaymentOptions"],
    disablePaymentWallet: json["disablePaymentWallet"] == null ? null : json["disablePaymentWallet"],
    disableTip: json["disableTip"] == null ? null : json["disableTip"],
    ebtCashCashback: json["ebtCashCashback"] == null ? null : json["ebtCashCashback"],
    incrementalAuthWithCard: json["incrementalAuthWithCard"] == null ? null : json["incrementalAuthWithCard"],
    isBalanceInquiry: json["isBalanceInquiry"] == null ? null : json["isBalanceInquiry"],
    manualEntry: json["manualEntry"] == null ? null : json["manualEntry"],
    multiTender: json["multiTender"] == null ? null : json["multiTender"],
    nonReferencedCredit: json["nonReferencedCredit"] == null ? null : json["nonReferencedCredit"],
    notes: json["notes"] == null ? null : json["notes"],
    offlineAuth: json["offlineAuth"] == null ? null : json["offlineAuth"],
    order: json["order"] == null ? null : Order.fromJson(json["order"]),
    orderId: json["orderId"] == null ? null : json["orderId"],
    readCardDataOnly: json["readCardDataOnly"] == null ? null : json["readCardDataOnly"],
    referenceId: json["referenceId"] == null ? null : json["referenceId"],
    skipPaymentConfirmationScreen: json["skipPaymentConfirmationScreen"] == null ? null : json["skipPaymentConfirmationScreen"],
    skipReceiptScreen: json["skipReceiptScreen"] == null ? null : json["skipReceiptScreen"],
    skipSignatureScreen: json["skipSignatureScreen"] == null ? null : json["skipSignatureScreen"],
    status: json["status"] == null ? null :statusFromText(json["status"]),
    tipAmount: json["tipAmount"] == null ? null : json["tipAmount"],
    transactions: json["transactions"] == null ? [] : List<Transaction>.from(json["transactions"].map((x) => Transaction.fromJson(x))),
    voucher: json["voucher"] == null ? null : json["voucher"],
  );

  Map<String, dynamic> toJson() => {
    // "adjustToAddCharges": adjustToAddCharges == null ? null : adjustToAddCharges,
    // "amount": amount == null ? null : amount,
    // "applicationIndex": applicationIndex == null ? null : applicationIndex,
    // "authzOnly": authzOnly == null ? null : authzOnly,
    // "autoClose": autoClose == null ? null : autoClose,
    // "autoPrintReceipt": autoPrintReceipt == null ? null : autoPrintReceipt,
    // "callerPackageName": callerPackageName == null ? null : callerPackageName,
    // "captureWithCard": captureWithCard == null ? null : captureWithCard,
    // "cashOnly": cashOnly == null ? null : cashOnly,
    // "cashbackAmount": cashbackAmount == null ? null : cashbackAmount,
    // "creditOnly": creditOnly == null ? null : creditOnly,
    // "currency": currency == null ? null : currency,
    // "debitOnly": debitOnly == null ? null : debitOnly,
    // "disableCash": disableCash == null ? null : disableCash,
    // "disableChangeAmount": disableChangeAmount == null ? null : disableChangeAmount,
    // "disableCheck": disableCheck == null ? null : disableCheck,
    // "disableDebit": disableDebit == null ? null : disableDebit,
    // "disableDebitCards": disableDebitCards == null ? null : disableDebitCards,
    // "disableEMVCL": disableEMVCL == null ? null : disableEMVCL,
    // "disableEMVCT": disableEMVCT == null ? null : disableEMVCT,
    // "disableEbtCashBenefits": disableEbtCashBenefits == null ? null : disableEbtCashBenefits,
    // "disableEbtFoodStamps": disableEbtFoodStamps == null ? null : disableEbtFoodStamps,
    // "disableEbtVoucher": disableEbtVoucher == null ? null : disableEbtVoucher,
    // "disableMSR": disableMSR == null ? null : disableMSR,
    // "disableManual": disableManual == null ? null : disableManual,
    // "disableOther": disableOther == null ? null : disableOther,
    // "disablePaymentOptions": disablePaymentOptions == null ? null : disablePaymentOptions,
    // "disablePaymentWallet": disablePaymentWallet == null ? null : disablePaymentWallet,
    // "disableTip": disableTip == null ? null : disableTip,
    // "ebtCashCashback": ebtCashCashback == null ? null : ebtCashCashback,
    // "incrementalAuthWithCard": incrementalAuthWithCard == null ? null : incrementalAuthWithCard,
    // "isBalanceInquiry": isBalanceInquiry == null ? null : isBalanceInquiry,
    // "manualEntry": manualEntry == null ? null : manualEntry,
    // "multiTender": multiTender == null ? null : multiTender,
    // "nonReferencedCredit": nonReferencedCredit == null ? null : nonReferencedCredit,
    // "notes": notes == null ? null : notes,
    // "offlineAuth": offlineAuth == null ? null : offlineAuth,
    // "order": order == null ? null : order!.toJson(),
    // "orderId": orderId == null ? null : orderId,
    // "readCardDataOnly": readCardDataOnly == null ? null : readCardDataOnly,
    // "referenceId": referenceId == null ? null : referenceId,
    // "skipPaymentConfirmationScreen": skipPaymentConfirmationScreen == null ? null : skipPaymentConfirmationScreen,
    // "skipReceiptScreen": skipReceiptScreen == null ? null : skipReceiptScreen,
    // "skipSignatureScreen": skipSignatureScreen == null ? null : skipSignatureScreen,
    // "status": status == null ? null : status,
    // "tipAmount": tipAmount == null ? null : tipAmount,
    // "transactions": transactions == null ? null : List<dynamic>.from(transactions!.map((x) => x.toJson())),
    // "voucher": voucher == null ? null : voucher,
  };


  static PaymentStatus? statusFromText(String statusText) {
    switch (statusText) {
      case 'AUTHORIZED':
        return PaymentStatus.AUTHORIZED;
      case 'CANCELED':
        return PaymentStatus.CANCELED;
      case 'COMPLETED':
        return PaymentStatus.COMPLETED;
      case 'DECLINED':
        return PaymentStatus.DECLINED;
      case 'FAILED':
        return PaymentStatus.FAILED;
      case 'HOLD':
        return PaymentStatus.HOLD;
      case 'READ_CARD_ONLY':
        return PaymentStatus.READ_CARD_ONLY;
      case 'REFUNDED':
        return PaymentStatus.REFUNDED;
      case 'VOIDED':
        return PaymentStatus.VOIDED;
      default:
        return null;
    }
  }
}


class Order {
  Order({
    this.amounts,
    this.id,
    this.items,
    this.notes,
    this.orderNumber,
    this.statuses,
  });

  OrderAmounts? amounts;
  String? id;
  List<Item>? items;
  String? notes;
  String? orderNumber;
  Statuses? statuses;

  factory Order.fromJson(Map<String, dynamic> json) => Order(
    amounts: json["amounts"] == null ? null : OrderAmounts.fromJson(json["amounts"]),
    id: json["id"] == null ? null : json["id"],
    items: json["items"] == null ? null : List<Item>.from(json["items"].map((x) => Item.fromJson(x))),
    notes: json["notes"] == null ? null : json["notes"],
    orderNumber: json["orderNumber"] == null ? null : json["orderNumber"],
    statuses: json["statuses"] == null ? null : Statuses.fromJson(json["statuses"]),
  );

  Map<String, dynamic> toJson() => {
    // "amounts": amounts == null ? null : amounts.toJson(),
    // "id": id == null ? null : id,
    // "items": items == null ? null : List<dynamic>.from(items.map((x) => x.toJson())),
    // "notes": notes == null ? null : notes,
    // "orderNumber": orderNumber == null ? null : orderNumber,
    // "statuses": statuses == null ? null : statuses.toJson(),
  };
}

class OrderAmounts {
  OrderAmounts({
    this.currency,
    this.discountTotal,
    this.feeTotal,
    this.netTotal,
    this.subTotal,
    this.taxTotal,
  });

  String? currency;
  int? discountTotal;
  int? feeTotal;
  int? netTotal;
  int? subTotal;
  int? taxTotal;

  factory OrderAmounts.fromJson(Map<String, dynamic> json) => OrderAmounts(
    currency: json["currency"] == null ? null : json["currency"],
    discountTotal: json["discountTotal"] == null ? null : json["discountTotal"],
    feeTotal: json["feeTotal"] == null ? null : json["feeTotal"],
    netTotal: json["netTotal"] == null ? null : json["netTotal"],
    subTotal: json["subTotal"] == null ? null : json["subTotal"],
    taxTotal: json["taxTotal"] == null ? null : json["taxTotal"],
  );

  Map<String, dynamic> toJson() => {
    "currency": currency == null ? null : currency,
    "discountTotal": discountTotal == null ? null : discountTotal,
    "feeTotal": feeTotal == null ? null : feeTotal,
    "netTotal": netTotal == null ? null : netTotal,
    "subTotal": subTotal == null ? null : subTotal,
    "taxTotal": taxTotal == null ? null : taxTotal,
  };
}

class Item {
  Item({
    this.discount,
    this.name,
    this.quantity,
    this.status,
    this.tax,
    this.unitOfMeasure,
    this.unitPrice,
  });

  int? discount;
  String? name;
  double? quantity;
  String? status;
  int? tax;
  String? unitOfMeasure;
  int? unitPrice;

  factory Item.fromJson(Map<String, dynamic> json) => Item(
    discount: json["discount"] == null ? null : json["discount"],
    name: json["name"] == null ? null : json["name"],
    quantity: json["quantity"] == null ? null : json["quantity"],
    status: json["status"] == null ? null : json["status"],
    tax: json["tax"] == null ? null : json["tax"],
    unitOfMeasure: json["unitOfMeasure"] == null ? null : json["unitOfMeasure"],
    unitPrice: json["unitPrice"] == null ? null : json["unitPrice"],
  );

  Map<String, dynamic> toJson() => {
    "discount": discount == null ? null : discount,
    "name": name == null ? null : name,
    "quantity": quantity == null ? null : quantity,
    "status": status == null ? null : status,
    "tax": tax == null ? null : tax,
    "unitOfMeasure": unitOfMeasure == null ? null : unitOfMeasure,
    "unitPrice": unitPrice == null ? null : unitPrice,
  };
}

class Statuses {
  Statuses({
    this.status,
  });

  String? status;

  factory Statuses.fromJson(Map<String, dynamic> json) => Statuses(
    status: json["status"] == null ? null : json["status"],
  );

  Map<String, dynamic> toJson() => {
    "status": status == null ? null : status,
  };
}

class Transaction {
  Transaction({
    this.action,
    this.amounts,
    this.context,
    this.customerLanguage,
    this.fundingSource,
    this.id,
    this.notes,
    this.references,
    this.status,
  });

  String? action;
  TransactionAmounts? amounts;
  Context? context;
  String? customerLanguage;
  FundingSource? fundingSource;
  String? id;
  String? notes;
  List<Reference>? references;
  String? status;

  factory Transaction.fromJson(Map<String, dynamic> json) => Transaction(
    action: json["action"] == null ? null : json["action"],
    amounts: json["amounts"] == null ? null : TransactionAmounts.fromJson(json["amounts"]),
    context: json["context"] == null ? null : Context.fromJson(json["context"]),
    customerLanguage: json["customerLanguage"] == null ? null : json["customerLanguage"],
    fundingSource: json["fundingSource"] == null ? null : FundingSource.fromJson(json["fundingSource"]),
    id: json["id"] == null ? null : json["id"],
    notes: json["notes"] == null ? null : json["notes"],
    references: json["references"] == null ? null : List<Reference>.from(json["references"].map((x) => Reference.fromJson(x))),
    status: json["status"] == null ? null : json["status"],
  );

  Map<String, dynamic> toJson() => {
    // "action": action == null ? null : action,
    // "amounts": amounts == null ? null : amounts.toJson(),
    // "context": context == null ? null : context.toJson(),
    // "customerLanguage": customerLanguage == null ? null : customerLanguage,
    // "fundingSource": fundingSource == null ? null : fundingSource.toJson(),
    // "id": id == null ? null : id,
    // "notes": notes == null ? null : notes,
    // "references": references == null ? null : List<dynamic>.from(references.map((x) => x.toJson())),
    // "status": status == null ? null : status,
  };
}

class TransactionAmounts {
  TransactionAmounts({
    this.cashbackAmount,
    this.currency,
    this.orderAmount,
    this.tipAmount,
    this.transactionAmount,
  });

  int? cashbackAmount;
  String? currency;
  int? orderAmount;
  int? tipAmount;
  int? transactionAmount;

  factory TransactionAmounts.fromJson(Map<String, dynamic> json) => TransactionAmounts(
    cashbackAmount: json["cashbackAmount"] == null ? null : json["cashbackAmount"],
    currency: json["currency"] == null ? null : json["currency"],
    orderAmount: json["orderAmount"] == null ? null : json["orderAmount"],
    tipAmount: json["tipAmount"] == null ? null : json["tipAmount"],
    transactionAmount: json["transactionAmount"] == null ? null : json["transactionAmount"],
  );

  Map<String, dynamic> toJson() => {
    "cashbackAmount": cashbackAmount == null ? null : cashbackAmount,
    "currency": currency == null ? null : currency,
    "orderAmount": orderAmount == null ? null : orderAmount,
    "tipAmount": tipAmount == null ? null : tipAmount,
    "transactionAmount": transactionAmount == null ? null : transactionAmount,
  };
}

class Context {
  Context({
    this.businessId,
    this.businessType,
    this.employeeUserId,
    this.mcc,
    this.mid,
    this.source,
    this.sourceApp,
    this.storeAddressCity,
    this.storeAddressTerritory,
    this.storeId,
    this.storeTimezone,
    this.tid,
  });

  String? businessId;
  String? businessType;
  int? employeeUserId;
  String? mcc;
  String? mid;
  String? source;
  String? sourceApp;
  String? storeAddressCity;
  String? storeAddressTerritory;
  String? storeId;
  String? storeTimezone;
  String? tid;

  factory Context.fromJson(Map<String, dynamic> json) => Context(
    businessId: json["businessId"] == null ? null : json["businessId"],
    businessType: json["businessType"] == null ? null : json["businessType"],
    employeeUserId: json["employeeUserId"] == null ? null : json["employeeUserId"],
    mcc: json["mcc"] == null ? null : json["mcc"],
    mid: json["mid"] == null ? null : json["mid"],
    source: json["source"] == null ? null : json["source"],
    sourceApp: json["sourceApp"] == null ? null : json["sourceApp"],
    storeAddressCity: json["storeAddressCity"] == null ? null : json["storeAddressCity"],
    storeAddressTerritory: json["storeAddressTerritory"] == null ? null : json["storeAddressTerritory"],
    storeId: json["storeId"] == null ? null : json["storeId"],
    storeTimezone: json["storeTimezone"] == null ? null : json["storeTimezone"],
    tid: json["tid"] == null ? null : json["tid"],
  );

  Map<String, dynamic> toJson() => {
    "businessId": businessId == null ? null : businessId,
    "businessType": businessType == null ? null : businessType,
    "employeeUserId": employeeUserId == null ? null : employeeUserId,
    "mcc": mcc == null ? null : mcc,
    "mid": mid == null ? null : mid,
    "source": source == null ? null : source,
    "sourceApp": sourceApp == null ? null : sourceApp,
    "storeAddressCity": storeAddressCity == null ? null : storeAddressCity,
    "storeAddressTerritory": storeAddressTerritory == null ? null : storeAddressTerritory,
    "storeId": storeId == null ? null : storeId,
    "storeTimezone": storeTimezone == null ? null : storeTimezone,
    "tid": tid == null ? null : tid,
  };
}

class FundingSource {
  FundingSource({
    this.type,
  });

  String? type;

  factory FundingSource.fromJson(Map<String, dynamic> json) => FundingSource(
    type: json["type"] == null ? null : json["type"],
  );

  Map<String, dynamic> toJson() => {
    "type": type == null ? null : type,
  };
}

class Reference {
  Reference({
    this.customType,
    this.id,
    this.type,
  });

  String? customType;
  String? id;
  String? type;

  factory Reference.fromJson(Map<String, dynamic> json) => Reference(
    customType: json["customType"] == null ? null : json["customType"],
    id: json["id"] == null ? null : json["id"],
    type: json["type"] == null ? null : json["type"],
  );

  Map<String, dynamic> toJson() => {
    "customType": customType == null ? null : customType,
    "id": id == null ? null : id,
    "type": type == null ? null : type,
  };
}


