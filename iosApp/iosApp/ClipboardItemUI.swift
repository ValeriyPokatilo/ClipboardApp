//
//  ClipboardItemUI.swift
//  iosApp
//
//  Created by Valeriy P on 17.11.2025.
//

import Foundation
import Shared

struct ClipboardItemUI: Identifiable, Hashable {
    let item: ClipboardItem
    var id: Int { Int(item.id) }
    var title: String { item.title }
    var value: String { item.value }
}
