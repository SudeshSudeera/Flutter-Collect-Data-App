import 'package:meta/meta.dart';
import 'dart:convert';

BusinessDetails businessDetailsFromJson(String str) => BusinessDetails.fromJson(json.decode(str));

String businessDetailsToJson(BusinessDetails data) => json.encode(data.toJson());

class BusinessDetails {
    String businessId;
    String businessName;
    String businessType;
    String businessOwnerType;
    DateTime registerDate;
    String resourceUrl;
    AddressDetails addressDetails;
    ContactDetailsDto contactDetailsDto;

    BusinessDetails({
        required this.businessId,
        required this.businessName,
        required this.businessType,
        required this.businessOwnerType,
        required this.registerDate,
        required this.resourceUrl,
        required this.addressDetails,
        required this.contactDetailsDto,
    });

    factory BusinessDetails.fromJson(Map<String, dynamic> json) => BusinessDetails(
        businessId: json["businessId"],
        businessName: json["businessName"],
        businessType: json["businessType"],
        businessOwnerType: json["businessOwnerType"],
        registerDate: DateTime.parse(json["registerDate"]),
        resourceUrl: json["resourceUrl"],
        addressDetails: AddressDetails.fromJson(json["addressDetails"]),
        contactDetailsDto: ContactDetailsDto.fromJson(json["contactDetailsDTO"]),
    );

    Map<String, dynamic> toJson() => {
        "businessId": businessId,
        "businessName": businessName,
        "businessType": businessType,
        "businessOwnerType": businessOwnerType,
        "registerDate": registerDate.toIso8601String(),
        "resourceUrl": resourceUrl,
        "addressDetails": addressDetails.toJson(),
        "contactDetailsDTO": contactDetailsDto.toJson(),
    };
}

class AddressDetails {
    String addressId;
    String address;
    double longitude;
    double latitude;

    AddressDetails({
        required this.addressId,
        required this.address,
        required this.longitude,
        required this.latitude,
    });

    factory AddressDetails.fromJson(Map<String, dynamic> json) => AddressDetails(
        addressId: json["addressId"],
        address: json["address"],
        longitude: json["longitude"]?.toDouble(),
        latitude: json["latitude"]?.toDouble(),
    );

    Map<String, dynamic> toJson() => {
        "addressId": addressId,
        "address": address,
        "longitude": longitude,
        "latitude": latitude,
    };
}

class ContactDetailsDto {
    String contactId;
    String home;
    String mobile;

    ContactDetailsDto({
        required this.contactId,
        required this.home,
        required this.mobile,
    });

    factory ContactDetailsDto.fromJson(Map<String, dynamic> json) => ContactDetailsDto(
        contactId: json["contactId"],
        home: json["home"],
        mobile: json["mobile"],
    );

    Map<String, dynamic> toJson() => {
        "contactId": contactId,
        "home": home,
        "mobile": mobile,
    };
}