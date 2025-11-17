//
//  DetailsScreen.swift
//  iosApp
//
//  Created by Valeriy P on 16.11.2025.
//

import SwiftUI
import Shared

struct DetailsScreen: View {
    
    let item: ClipboardItem?
    
    @ObservedObject private var viewModel: DetailsViewModel
    @Environment(\.dismiss) private var dismiss

    init(item: ClipboardItem?) {
        self.item = item
        self.viewModel = DetailsViewModel(id: "\(String(describing: item?.id))")
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
                .background(.green)
                .cornerRadius(8)
            }
        }
        .padding(.horizontal)
    }
}

#Preview {
    DetailsScreen(item: ClipboardItem(id: 0, title: "Foo", value: "Bardak"))
}
