//
//  ClipboardItemRow.swift
//  iosApp
//
//  Created by Valeriy P on 15.11.2025.
//

import SwiftUI
import Shared

struct ClipboardItemRow: View {
    
    let item: ClipboardItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack { Spacer()}
            Text(item.title)
                .font(.system(size: 16, weight: .bold))
            Text(item.value)
                .font(.system(size: 20, weight: .semibold))
                .foregroundStyle(Color(.darkGray))
        }
    }
}

#Preview {
    ClipboardItemRow(item: ClipboardItem(id: 0, title: "Foo", value: "Bar"))
}
