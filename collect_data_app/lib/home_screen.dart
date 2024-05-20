// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:collect_data_app/add_business.dart';
import 'package:collect_data_app/utils/colors.dart';
import 'package:collect_data_app/view_business.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

final GlobalKey<ScaffoldState> key = GlobalKey();

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: Container(
        color: mainColor,
        height: 70,
        child: Padding(
          padding: EdgeInsets.symmetric(vertical: 10, horizontal: 10),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Expanded(
                child: GestureDetector(
                  onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const AddBusinessScreen()));
                  },
                  child: Container(
                    decoration: BoxDecoration(
                        border: Border.all(width: 1, color: Colors.white)),
                    child: Column(
                      children: [
                        Icon(
                          Icons.add,
                          color: Colors.white,
                          size: 25,
                        ),
                        Text(
                          "Add Business",
                          style: TextStyle(color: Colors.white, fontSize: 16),
                        )
                      ],
                    ),
                  ),
                ),
              ),
              SizedBox(
                width: 5,
              ),
              Expanded(
                child: GestureDetector(
                  onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const ViewBusiness()));
                  },
                  child: Container(
                    decoration: BoxDecoration(
                        border: Border.all(width: 1, color: Colors.white)),
                    child: Column(
                      children: [
                        Icon(
                          Icons.view_agenda,
                          color: Colors.white,
                          size: 25,
                        ),
                        Text(
                          "View Business",
                          style: TextStyle(color: Colors.white, fontSize: 16),
                        )
                      ],
                    ),
                  ),
                ),
              )
            ],
          ),
        ),
      ),
      key: key,
      drawer: Drawer(
        backgroundColor: mainColor,
      ),
      appBar: AppBar(
        leading: IconButton(
          onPressed: () {
            key.currentState?.openDrawer();
          },
          icon: const Icon(
            Icons.menu_open,
            color: Colors.white,
          ),
        ),
        backgroundColor: mainColor,
        title: const Text(
          "Collect Data",
          style: TextStyle(
              fontSize: 20, fontWeight: FontWeight.bold, color: Colors.white),
        ),
      ),
      body: Center(
        child: Image.network(
            "https://img.freepik.com/free-vector/people-analyzing-growth-charts-illustrated_23-2148865274.jpg?t=st=1713332944~exp=1713336544~hmac=b2b07ffacdff47fc3afbce7254f2bd95aecb41a84d0d8829610c703d68c0e1bb&w=740"),
      ),
    );
  }
}