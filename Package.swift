// swift-tools-version:5.2
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "NativeTestBase",
    platforms: [
        .iOS(.v10)
    ],
    products: [
        .library(
            name: "NativeTestBase",
            targets: ["NativeTestBase"]),
    ],
    dependencies: [],
    targets: [
        .target(
            name: "NativeTestBase",
            dependencies: ["NativeTestObjCBase"],
            path: "NativeTestBase",
            sources: ["Source"]),
        .target(
            name: "NativeTestObjCBase",
            path: "NativeTestBase",
            sources: ["ObjC"],
            publicHeadersPath: "")
    ]
)
