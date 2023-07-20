package ru.elvitalya.droiderhandbook.features.droiderhandbook.section

import com.arkivanov.decompose.ComponentContext

class RealSectionComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, SectionComponent {
}