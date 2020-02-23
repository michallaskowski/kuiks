//
//  ContentView.swift
//  SampleiOS
//
//  Created by mlaskowski on 08/02/2020.
//  Copyright © 2020 Michal Laskowski. All rights reserved.
//

import SwiftUI

// navigation works once on simulators till xcode11.4
// https://stackoverflow.com/questions/59279176/navigationlink-works-only-for-once

struct ContentView: View {
    var didTapList: (() -> Void)?

    var body: some View {
        Button(action: {
            self.didTapList?()
        }, label: {
            Text("Go to list")
        }).accessibility(identifier: "show_list")

    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
