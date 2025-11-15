//
//  ClipboardScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.11.2025.
//

import SwiftUI
import Shared

struct ClipboardScreen: View {
    
    @ObservedObject private var viewModel = ClipboardViewModel()
    
    var body: some View {
        List {
            ForEach(viewModel.state(\.items), id: \.id) {
                ClipboardItemRow(item: $0)
            }
        }
    }
}

#Preview {
    ClipboardScreen()
}
