//
//  DetailsScreen.swift
//  iosApp
//
//  Created by Valeriy P on 16.11.2025.
//

import Shared
import SwiftUI

struct DetailsScreen: View {

    @ObservedObject private var viewModel: DetailsViewModel
    @Environment(\.dismiss) private var dismiss

    let item: ClipboardItem?

    private var isDisabled: Bool {
        viewModel.binding(\.title).wrappedValue.isEmpty
            && viewModel.binding(\.value).wrappedValue.isEmpty
    }

    init(item: ClipboardItem?) {
        self.item = item
        self.viewModel = DetailsViewModel(id: item.flatMap { String($0.id) })
    }

    var body: some View {
        VStack {
            TextField("Title", text: viewModel.binding(\.title))
                .textFieldStyle(.roundedBorder)
            TextField("Value", text: viewModel.binding(\.value))
                .textFieldStyle(.roundedBorder)
            HStack(spacing: 20) {
                Button {
                    dismiss()
                } label: {
                    Text("Cancel")
                }
                .frame(maxWidth: .infinity)
                .frame(height: 40)
                .foregroundStyle(.white)
                .background(.red)
                .cornerRadius(8)

                Button {
                    viewModel.saveItem()
                    // *** async
                    dismiss()
                } label: {
                    Text("Save")
                }
                .frame(maxWidth: .infinity)
                .frame(height: 40)
                .foregroundStyle(.white)
                .background(isDisabled ? .gray : .green)
                .cornerRadius(8)
                .disabled(isDisabled)
            }
        }
        .padding(.horizontal)
    }
}

#Preview {
    DetailsScreen(item: ClipboardItem(id: 0, title: "Foo", value: "Bardak"))
}
