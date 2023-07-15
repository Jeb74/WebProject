const navReference = document.getElementsByClassName("navigation")[0]
const contentReference = document.getElementsByTagName("main")[0]
const gNavBarPos = navReference.offsetTop

export function onScrollSetNav() {
    if (window.scrollY >= gNavBarPos) {
        navReference.style.position = "fixed"
        contentReference.classList.add("scroll-margin-main")
        return
    }
    navReference.style.position = "sticky";
    contentReference.classList.remove("scroll-margin-main")
}
