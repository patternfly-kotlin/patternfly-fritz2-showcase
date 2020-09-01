@file:Suppress("DuplicatedCode")

package org.patternfly.showcase.component

import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.render
import kotlinx.coroutines.flow.map
import org.patternfly.DropdownStore
import org.patternfly.Notification
import org.patternfly.Severity.INFO
import org.patternfly.Size
import org.patternfly.pfContent
import org.patternfly.pfDropdown
import org.patternfly.pfDropdownItem
import org.patternfly.pfDropdownItems
import org.patternfly.pfDropdownSeparator
import org.patternfly.pfSection
import org.patternfly.pfTitle
import org.w3c.dom.HTMLElement

object DropdownComponent : Iterable<Tag<HTMLElement>> {
    override fun iterator(): Iterator<Tag<HTMLElement>> = iterator {
        yield(render {
            intro(
                title = "Dropdown",
                prefix = "Use a ",
                key = "dropdown",
                text = " when you want to present a list of actions in a limited space."
            )
        })
        yield(render {
            pfSection {
                className = const("sc-component__buttons")
                pfContent {
                    h2 { +"Examples" }
                }
                snippet("Basic", DropdownCode.BASIC) {
                    pfDropdown(DropdownStore<String>(), "Dropdown") {
                        pfDropdownItems {
                            pfDropdownItem("Item 1")
                            pfDropdownItem("Disabled Item") {
                                disabled = true
                            }
                            pfDropdownSeparator()
                            pfDropdownItem("Separated Item")
                        }
                    }
                }
                snippet("With initial selection", DropdownCode.SELECTED) {
                    pfDropdown(DropdownStore<String>(), "Dropdown") {
                        pfDropdownItems {
                            pfDropdownItem("Item 1")
                            pfDropdownItem("Item 2") {
                                selected = true
                            }
                            pfDropdownItem("Disabled Item") {
                                disabled = true
                            }
                            pfDropdownSeparator()
                            pfDropdownItem("Separated Item")
                        }
                    }
                }
                snippet("Dropdown events", DropdownCode.EVENTS) {
                    val dropdown = pfDropdown(DropdownStore<String>(), "Dropdown") {
                        pfDropdownItems {
                            pfDropdownItem("Item 1")
                            pfDropdownItem("Disabled Item") {
                                disabled = true
                            }
                            pfDropdownSeparator()
                            pfDropdownItem("Separated Item")
                        }
                    }
                    dropdown.store.clicks
                        .map { Notification(INFO, "Clicked on $it") }
                        .handledBy(Notification.store.add)
                    dropdown.ces.collapsed
                        .map { Notification(INFO, "Dropdown collapsed") }
                        .handledBy(Notification.store.add)
                    dropdown.ces.expanded
                        .map { Notification(INFO, "Dropdown expanded") }
                        .handledBy(Notification.store.add)
                }
            }
        })
    }
}

internal object DropdownCode {

    //language=kotlin
    const val BASIC: String = """fun main() {
    render {
        pfDropdown(DropdownStore<String>(), "Dropdown") {
            pfDropdownItems {
                pfDropdownItem("Item 1")
                pfDropdownItem("Disabled Item") {
                    disabled = true
                }
                pfDropdownSeparator()
                pfDropdownItem("Separated Item")
            }
        }
    }
}"""

    //language=kotlin
    const val SELECTED: String = """fun main() {
    render {
        pfDropdown(DropdownStore<String>(), "Dropdown") {
            pfDropdownItems {
                pfDropdownItem("Item 1")
                pfDropdownItem("Item 2") {
                    selected = true
                }
                pfDropdownItem("Disabled Item") {
                    disabled = true
                }
                pfDropdownSeparator()
                pfDropdownItem("Separated Item")
            }
        }
    }
}"""

    //language=kotlin
    const val EVENTS: String = """fun main() {
    render {
        val dropdown = pfDropdown(DropdownStore<String>(), "Dropdown") {
            pfDropdownItems {
                pfDropdownItem("Item 1")
                pfDropdownItem("Disabled Item") {
                    disabled = true
                }
                pfDropdownSeparator()
                pfDropdownItem("Separated Item")
            }
        }
        dropdown.store.clicks.map { Notification(INFO, "Clicked on ${'$'}it") } handledBy Notification.store.add
        dropdown.ces.collapsed
            .map { Notification(INFO, "Dropdown collapsed") }
            .handledBy(Notification.store.add)
        dropdown.ces.expanded
            .map { Notification(INFO, "Dropdown expanded") }
            .handledBy(Notification.store.add)
    }
}"""
}