//  Created by Michał Laskowski on 20/02/2020.

import SwiftUI

extension Int: Identifiable {
    public typealias ID = Int
    public var id: ID {
        return self
    }
}

struct ListView: View {
    var didTapBack: (() -> Void)?

    private let numbers: [Int] = Array(1...99)

    var body: some View {
        List(numbers) { number in
            Text("\(number)").accessibility(identifier: "\(number)")
                .onTapGesture {
                    if number == 1 || number == 99 {
                        self.didTapBack?()
                    }
            }
        }.accessibility(identifier: "list")
    }
}


struct ListView_Previews: PreviewProvider {
    static var previews: some View {
        ListView()
    }
}
