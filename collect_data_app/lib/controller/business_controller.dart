import 'dart:convert';
import 'dart:io';
import 'package:collect_data_app/environment_variable/variable.dart';
import 'package:collect_data_app/model/business_details.dart';
import 'package:dio/dio.dart';
import 'package:intl/intl.dart';
import 'package:http/http.dart' as http;

Future<int?> saveBusiness(
    String businessName,
    String businessType,
    String businessOwnerType,
    String address,
    String longitude,
    String latitude,
    String home,
    String mobile,
    File? file) async {
  FormData formData = FormData.fromMap({
    "file": await MultipartFile.fromFile(file!.path),
    'data': json.encode({
      "businessName": businessName,
      "businessType": businessType,
      "businessOwnerType": businessOwnerType,
      "registerDate": DateFormat("yyyy-MM-dd").format(DateTime.now()),
      "address": address,
      "longitude": longitude,
      "latitude": latitude,
      "home": home,
      "mobile": mobile
    })
  });
  Dio dio = Dio();
  dio.options.headers = {
    'Content-type': 'multipart/form-data',
    'Accept': 'application/json'
  };

  final response = await dio.post('$api/business/create', data: formData);
  try {
    if (response.statusCode == 201) {
      return response.statusCode;
    }
  } catch (e) {
    print(e);
  }
}

Future<List<BusinessDetails>> getAllBusiness(int page, String searchText) async {
  final response = await http.get(Uri.parse(
      '$api/business/find-all-business?page=$page&size=10&searchText=$searchText'));

  if (response.statusCode == 200) {
    List<dynamic> jsonResponse = json.decode(response.body)['data']['dataList'];
    return jsonResponse.map((data) => BusinessDetails.fromJson(data)).toList();
  }
  return Future.error("error");
}

Future<bool> delete(String id) async {
  final response = await http.delete(Uri.parse('$api/business/$id'));

  if (response.statusCode == 200) {
    return true;
  }
  return false;
}

Future<BusinessDetails> getBusinessDetails(String id) async {
  final response = await http.get(Uri.parse('$api/business/get-by-id/$id'));
  if (response.statusCode == 200) {
    return BusinessDetails.fromJson(json.decode(response.body)['data']);
  }
  return Future.error("error");
}