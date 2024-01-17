const navReference = document.querySelector("nav")
const navPos = navReference.offsetTop
const contentReference = document.getElementsByTagName("main")[0]

const SCROLL_MARGIN = "scroll-margin-main"

export function onScrollSetNav() {
    if (window.scrollY >= navPos) {
        navReference.style.position = "fixed"
        contentReference.classList.add(SCROLL_MARGIN)
    }
    else {
        navReference.style.position = "sticky";
        contentReference.classList.remove(SCROLL_MARGIN)
    }
}
