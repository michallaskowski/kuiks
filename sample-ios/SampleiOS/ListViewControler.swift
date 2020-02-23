//
//  ListViewControler.swift
//  SampleiOS
//
//  Created by Michał Laskowski on 22/02/2020.
//  Copyright © 2020 Michał Laskowski. All rights reserved.
//

import UIKit

final class ListViewController: UITableViewController {
    var didTapBack: (() -> Void)?

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        tableView.accessibilityIdentifier = "list"
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)

        let labelText = "\(indexPath.row + 1)"
        cell.textLabel?.text = labelText
        cell.isAccessibilityElement = true
        cell.accessibilityIdentifier = labelText
        return cell
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 99
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.row == 0 || indexPath.row == 98 {
            didTapBack?()
        }
    }
}
