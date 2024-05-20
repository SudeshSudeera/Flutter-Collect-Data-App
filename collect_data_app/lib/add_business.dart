// ignore_for_file: prefer_const_constructors, sort_child_properties_last

import 'dart:async';
import 'dart:io';
import 'package:collect_data_app/controller/business_controller.dart';
import 'package:collect_data_app/utils/colors.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:image_picker/image_picker.dart';

class AddBusinessScreen extends StatefulWidget {
  const AddBusinessScreen({super.key});

  @override
  State<AddBusinessScreen> createState() => _AddBusinessScreenState();
}

final GlobalKey<FormState> key = GlobalKey<FormState>();

class _AddBusinessScreenState extends State<AddBusinessScreen> {
  TextEditingController businessNameController = TextEditingController();
  TextEditingController businessTypeController = TextEditingController();
  TextEditingController homeController = TextEditingController();
  TextEditingController mobileController = TextEditingController();
  TextEditingController longitudeController = TextEditingController();
  TextEditingController latitudeController = TextEditingController();
  TextEditingController addressController = TextEditingController();
  String selectedOwnerType = "";
  List<String> businessOwnerType = [
    "PRIVATE",
    "GOVERNMENT",
    "SOLO",
    "SEMI_GOVERNMENT",
    "NGO"
  ];
  File? image;
  final picker = ImagePicker();
  Future _imgFromCamera() async {
    final pickedFile =
        await picker.pickImage(source: ImageSource.camera, imageQuality: 50);
    setState(() {
      if (pickedFile != null) {
        image = File(pickedFile.path);
      }
    });
  }

  Future _imgFromGallery() async {
    final pickedFile =
        await picker.pickImage(source: ImageSource.gallery, imageQuality: 50);
    setState(() {
      if (pickedFile != null) {
        image = File(pickedFile.path);
      }
    });
  }

  void showImagePicker(context) {
    showModalBottomSheet(
        context: context,
        builder: (BuildContext bc) {
          return Container(
            child: Wrap(
              children: [
                ListTile(
                  leading: Icon(Icons.photo_library),
                  title: Text("Photo Gallery"),
                  onTap: () {
                    _imgFromGallery();
                    Navigator.pop(context);
                  },
                ),
                ListTile(
                  leading: Icon(Icons.camera_alt),
                  title: Text("Photo Camera"),
                  onTap: () {
                    _imgFromCamera();
                    Navigator.pop(context);
                  },
                )
              ],
            ),
            decoration: BoxDecoration(
                color: Colors.amber.shade50,
                borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(20),
                    topRight: Radius.circular(20))),
          );
        });
  }

  bool serviceStatus = false;
  bool hasPermission = false;
  late LocationPermission permission;
  late Position position;
  String long = "", lat = "";
  late StreamSubscription<Position> positionStream;

  checkGps() async {
    serviceStatus = await GeoLocator.isLocationServiceEnabled();
    if (serviceStatus) {
      permission = await GeoLocator.checkPermission();

      if (permission == LocationPermission.denied) {
        permission = await GeoLocator.requestPermission();
        if (permission == LocationPermission.denied) {
          showDialog<String>(
              context: context,
              builder: (BuildContext context) => alertDialog(
                  context, "Access denied", "Location permissions are denied"));
        } else if (permission == LocationPermission.deniedForever) {
          showDialog<String>(
              context: context,
              builder: (BuildContext context) => alertDialog(
                  context,
                  "Access denied",
                  "Location permissions are permanently denied"));
        } else {
          hasPermission = true;
        }
      } else {
        hasPermission = true;
      }

      if (hasPermission) {
        setState(() {});

        getLocation();
      }
    } else {
      showDialog<String>(
          context: context,
          builder: (BuildContext context) => alertDialog(
              context,
              "Access denied",
              "GPS Service is not enabled, turn on GPS location"));
    }

    setState(() {});
  }

  AlertDialog alertDialog(BuildContext context, String title, String content) {
    return AlertDialog(
      title: Text(title),
      content: Text(content),
      actions: <Widget>[
        TextButton(
          onPressed: () => Navigator.pop(context, 'Cancel'),
          child: const Text('Cancel'),
        ),
        TextButton(
          onPressed: () => Navigator.pop(context, 'OK'),
          child: const Text('OK'),
        ),
      ],
    );
  }

  getLocation() async {
    position = await GeoLocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high);

    long = position.longitude.toString();
    lat = position.latitude.toString();

    setState(() {
      longitudeController.text = lat;
      latitudeController.text = long;
    });

    LocationSettings locationSettings = const LocationSettings(
      accuracy: LocationAccuracy.high,
      distanceFilter: 100,
    );

    StreamSubscription<Position> positionStream =
        GeoLocator.getPositionStream(locationSettings: locationSettings)
            .listen((Position position) {
      long = position.longitude.toString();
      lat = position.latitude.toString();

      setState(() {});
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        titleSpacing: 0,
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: const Icon(
            Icons.arrow_back,
            color: Colors.white,
          ),
        ),
        backgroundColor: mainColor,
        title: const Text(
          "Add Business",
          style: TextStyle(
              fontSize: 20, fontWeight: FontWeight.bold, color: Colors.white),
        ),
      ),
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.all(8),
          children: [
            Form(
                key: key,
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(top: 5.0),
                      child: TextFormField(
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return "Please fill your business name";
                          }
                          return null;
                        },
                        controller: businessNameController,
                        decoration: const InputDecoration(
                          hintText: "Business Name",
                          border: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromARGB(255, 203, 202, 202))),
                          hintStyle: TextStyle(
                              fontWeight: FontWeight.w500, fontSize: 14),
                          enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromARGB(255, 203, 202, 202))),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10.0),
                      child: TextFormField(
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return "Please fill your business type";
                          }
                          return null;
                        },
                        controller: businessTypeController,
                        decoration: const InputDecoration(
                          hintText: "Business Type",
                          border: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromARGB(255, 203, 202, 202))),
                          hintStyle: TextStyle(
                              fontWeight: FontWeight.w500, fontSize: 14),
                          enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromARGB(255, 203, 202, 202))),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10.0),
                      child: DropdownButtonFormField(
                          decoration: const InputDecoration(
                            hintText: "Business Owner Type",
                            border: OutlineInputBorder(
                                borderSide: BorderSide(
                                    color: Color.fromARGB(255, 203, 202, 202))),
                            hintStyle: TextStyle(
                                fontWeight: FontWeight.w500, fontSize: 14),
                            enabledBorder: OutlineInputBorder(
                                borderSide: BorderSide(
                                    color: Color.fromARGB(255, 203, 202, 202))),
                          ),
                          items: businessOwnerType
                              .map((ownerType) => DropdownMenuItem<String>(
                                  value: ownerType, child: Text(ownerType)))
                              .toList(),
                          onChanged: (value) {
                            setState(() {
                              selectedOwnerType = value!;
                            });
                          }),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10.0),
                      child: TextFormField(
                        maxLines: 5,
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return "Please fill your address";
                          }
                          return null;
                        },
                        controller: addressController,
                        decoration: const InputDecoration(
                          hintText: "Address",
                          border: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromARGB(255, 203, 202, 202))),
                          hintStyle: TextStyle(
                              fontWeight: FontWeight.w500, fontSize: 14),
                          enabledBorder: OutlineInputBorder(
                              borderSide: BorderSide(
                                  color: Color.fromARGB(255, 203, 202, 202))),
                        ),
                      ),
                    ),
                    Padding(
                        padding: const EdgeInsets.only(top: 10.0),
                        child: Row(
                          children: [
                            Expanded(
                              flex: 3,
                              child: TextFormField(
                                readOnly: true,
                                validator: (value) {
                                  if (value == null || value.isEmpty) {
                                    return "Please fill your longitude";
                                  }
                                  return null;
                                },
                                controller: longitudeController,
                                decoration: const InputDecoration(
                                  hintText: "Longitude",
                                  border: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                  hintStyle: TextStyle(
                                      fontWeight: FontWeight.w500,
                                      fontSize: 14),
                                  enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                ),
                              ),
                            ),
                            SizedBox(
                              width: 5,
                            ),
                            Expanded(
                              flex: 3,
                              child: TextFormField(
                                readOnly: true,
                                validator: (value) {
                                  if (value == null || value.isEmpty) {
                                    return "Please fill your latitude";
                                  }
                                  return null;
                                },
                                controller: latitudeController,
                                decoration: const InputDecoration(
                                  hintText: "Latitude",
                                  border: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                  hintStyle: TextStyle(
                                      fontWeight: FontWeight.w500,
                                      fontSize: 14),
                                  enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                ),
                              ),
                            ),
                            SizedBox(
                              width: 5,
                            ),
                            Expanded(
                                child: Container(
                              decoration: BoxDecoration(
                                  color: Colors.green,
                                  borderRadius: BorderRadius.circular(3)),
                              width: 35,
                              height: 60,
                              child: IconButton(
                                onPressed: checkGps,
                                icon: Icon(Icons.my_location),
                              ),
                            ))
                          ],
                        )),
                    Padding(
                        padding: const EdgeInsets.only(top: 10.0),
                        child: Row(
                          children: [
                            Expanded(
                              child: TextFormField(
                                keyboardType: TextInputType.phone,
                                validator: (value) {
                                  if (value == null || value.isEmpty) {
                                    return "Please fill your home";
                                  }
                                  return null;
                                },
                                controller: homeController,
                                decoration: const InputDecoration(
                                  hintText: "Home",
                                  border: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                  hintStyle: TextStyle(
                                      fontWeight: FontWeight.w500,
                                      fontSize: 14),
                                  enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                ),
                              ),
                            ),
                            SizedBox(
                              width: 5,
                            ),
                            Expanded(
                              child: TextFormField(
                                keyboardType: TextInputType.phone,
                                validator: (value) {
                                  if (value == null || value.isEmpty) {
                                    return "Please fill your mobile";
                                  }
                                  return null;
                                },
                                controller: mobileController,
                                decoration: const InputDecoration(
                                  hintText: "Mobile",
                                  border: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                  hintStyle: TextStyle(
                                      fontWeight: FontWeight.w500,
                                      fontSize: 14),
                                  enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: Color.fromARGB(
                                              255, 203, 202, 202))),
                                ),
                              ),
                            ),
                          ],
                        )),
                    Padding(
                        padding: const EdgeInsets.only(top: 10.0),
                        child: Container(
                            decoration: BoxDecoration(
                                border:
                                    Border.all(color: Colors.grey, width: 1)),
                            height: 120,
                            width: double.infinity,
                            child: image == null
                                ? IconButton(
                                    onPressed: () {
                                      showImagePicker(context);
                                    },
                                    icon: Icon(
                                      Icons.photo,
                                      color: Colors.grey,
                                    ),
                                  )
                                : GestureDetector(
                                    onTap: () {
                                      showImagePicker(context);
                                    },
                                    child: Container(
                                      decoration: BoxDecoration(
                                          image: DecorationImage(
                                              image: FileImage(image!),
                                              fit: BoxFit.cover)),
                                    ),
                                  ))),
                    Padding(
                        padding: const EdgeInsets.only(top: 10.0),
                        child: MaterialButton(
                          color: mainColor,
                          onPressed: () async {
                            if (key.currentState!.validate()) {
                              int? code = await saveBusiness(
                                  businessNameController.text,
                                  businessTypeController.text,
                                  selectedOwnerType,
                                  addressController.text,
                                  longitudeController.text,
                                  latitudeController.text,
                                  homeController.text,
                                  mobileController.text,
                                  image);
                              if (code == 201) {
                                Future.delayed(Duration(seconds: 3))
                                    .then((value) {
                                  showDialog(
                                      context: context,
                                      builder: (BuildContext context) =>
                                          AlertDialog(
                                            title: Text("Business Saved"),
                                            content: Text("Business Saved"),
                                            actions: [
                                              TextButton(
                                                  onPressed: () {
                                                    Navigator.pop(context);
                                                  },
                                                  child: Text("Ok"))
                                            ],
                                          ));
                                });
                                businessNameController.clear();
                                businessTypeController.clear();
                                addressController.clear();
                                longitudeController.clear();
                                latitudeController.clear();
                                homeController.clear();
                                mobileController.clear();
                              }
                            }
                          },
                          minWidth: double.infinity,
                          height: 50,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(5)),
                          child: Text(
                            "Submit",
                            style: TextStyle(color: Colors.white, fontSize: 18),
                          ),
                        ))
                  ],
                ))
          ],
        ),
      ),
    );
  }
}