import 'dart:convert';

import 'package:device_info_plus/device_info_plus.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:native_code_test/payment.dart';
import 'package:native_code_test/product.dart';
import 'dart:io';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform =  MethodChannel('smsAggregationChannel');
  List<Product> products = <Product>[];

  @override
  void initState() {
    init();
    products.add(Product(productName: 'Product', unitPrice: 9.0, discount: 2.0, quantity: 3, tax: 2, unitOfMeasure: 1));
    products.add(Product(productName: 'Product2', unitPrice: 9.0, discount: 2.0, quantity: 3, tax: 2, unitOfMeasure: 1));
    super.initState();
  }

  Future<void> init() async {
    if (Platform.isAndroid) {
      DeviceInfoPlugin deviceInfo = DeviceInfoPlugin();
      AndroidDeviceInfo androidInfo = await deviceInfo.androidInfo;
      print('Running on ${androidInfo.model}');
      print('Running on ${androidInfo.brand}');
      print('Running on ${androidInfo.device}');
      print('Running on ${androidInfo.manufacturer}');
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(body: Center(child:  ElevatedButton(
      child: const Text('Start Aggregate'),
      onPressed: _startAggregation,
    ),),);

  }

  Future<void> _startAggregation() async {
    try {

      await platform.invokeMethod('startAggregation', {// start the aggregation with args
        'arg1': json.encode(products),
      //  'arg1': products.map((e) => e.toJson()).toString()
      }).then((value) {

        final payment = Payment.fromJson(json.decode(value));

        print('------------------------------------');
        print(payment.status);
      });

    } on PlatformException catch (e) {
      print("Failed to start SDK");
    }
  }



}
