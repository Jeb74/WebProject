const navReference = document.getElementsByClassName("navigation")[0]
const contentReference = document.getElementsByTagName("main")[0]
const gNavBarPos = navReference.offsetTop

export function trackCurrentPage() {
    const cPage = window.location.href;
    const elements = Array.from(document.getElementsByClassName("navigation-stage"))
    const element = elements.find(e => cPage.search(e.getAttribute("href").split(".")[0]));
    
    element.style.backgroundColor = "#2A2A2A"
}

export function onScrollSetNav() {
    if (window.scrollY >= gNavBarPos) {
        navReference.style.position = "fixed"
        contentReference.classList.add("scroll-margin-main")
        return
    }
    navReference.style.position = "sticky";
    contentReference.classList.remove("scroll-margin-main")
}
