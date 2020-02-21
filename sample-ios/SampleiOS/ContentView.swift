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
    var body: some View {
        NavigationView {
            NavigationLink(destination: ListView()) {
                Text("Go to List").accessibility(identifier: "show_list")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
