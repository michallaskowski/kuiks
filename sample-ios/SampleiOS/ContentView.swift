//
//  ContentView.swift
//  SampleiOS
//
//  Created by mlaskowski on 08/02/2020.
//  Copyright © 2020 Michal Laskowski. All rights reserved.
//

import SwiftUI

enum ContentViewAction {
    case goToList
    case goToNetwork
}

// navigation works once on simulators till xcode11.4
// https://stackoverflow.com/questions/59279176/navigationlink-works-only-for-once
struct ContentView: View {
    var didMakeAction: ((ContentViewAction) -> Void)?

    var body: some View {
        VStack {
            Button(action: {
                self.didMakeAction?(.goToList)
            }, label: { Text("Go to list") })
                .accessibility(identifier: "show_list")

            Spacer(minLength: 40).fixedSize()

            Button(action: {
                self.didMakeAction?(.goToNetwork)
            }, label: { Text("Go to GitHub contributors") })
                .accessibility(identifier: "contributors")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
