//
//  ClipboardScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.11.2025.
//

import Shared
import SwiftUI

struct ClipboardScreen: View {

    @ObservedObject private var viewModel = ClipboardViewModel()
    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            List {
                ForEach(
                    viewModel.state(\.items).map { ClipboardItemUI(item: $0) },
                    id: \.id
                ) { itemUI in
                    ClipboardItemRow(item: itemUI.item)
                        .onTapGesture {
                            viewModel.doCopyToClipboard(text: itemUI.value)
                        }
                        .swipeActions {
                            Button {
                                viewModel.deleteItem(id: itemUI.item.id)
                            } label: {
                                Label("Delete", image: "trash")
                            }
                            .tint(.red)
                        }
                }
            }
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        path.append(nil as ClipboardItem?)
                    } label: {
                        Image(systemName: "plus")
                    }
                }
            }
            .navigationTitle("Tap to copy")
            .navigationDestination(for: ClipboardItem?.self) { item in
                DetailsScreen(item: item)
            }
        }
    }
}

#Preview {
    ClipboardScreen()
}
