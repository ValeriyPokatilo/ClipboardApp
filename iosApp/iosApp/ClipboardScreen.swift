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
    
    private var items: [ClipboardItemUI] {
        viewModel.state(\.items).map { ClipboardItemUI(item: $0) }
    }

    var body: some View {
        NavigationStack(path: $path) {
            List {
                ForEach(
                    items,
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
                                Label("Delete", systemImage: "trash")
                            }
                            .tint(.red)

                            Button {
                                path.append(Route.edit(itemUI.item))
                            } label: {
                                Label("Edit", systemImage: "pencil")
                            }
                            .tint(.green)
                        }
                }
            }
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        path.append(Route.create)
                    } label: {
                        Image(systemName: "plus")
                    }
                }
            }
            .navigationTitle("Tap to copy")
            .navigationDestination(for: Route.self) { route in
                switch route {
                case .edit(let item):
                    DetailsScreen(item: item)

                case .create:
                    DetailsScreen(item: nil)
                }
            }
        }
    }
}

#Preview {
    ClipboardScreen()
}
