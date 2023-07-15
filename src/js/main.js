import {onScrollSetNav} from "./navigation.js";
import {modifyFontSize} from "./styling_scripts.js";

function onLoadGetPage() {
    window.onscroll = function () {
        onScrollSetNav()
    }

    const buttons = document.getElementsByClassName("letter-resizer")
    for (let i = 0; i < buttons.length; i++) {
        switch (buttons[i].classList[0]) {
            case "small":
                buttons[i].onclick = function () {
                    modifyFontSize("small")
                }
                break
            case "medium":
                buttons[i].onclick = function () {
                    modifyFontSize("medium")
                }
                break
            case "large":
                buttons[i].onclick = function () {
                    modifyFontSize("large")
                }
                break
        }
    }
}

window.onload = function() { onLoadGetPage() }