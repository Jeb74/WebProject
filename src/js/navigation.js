const navReference = document.getElementsByClassName("navigation")[0]
const contentReference = document.getElementsByTagName("main")[0]
const gNavBarPos = navReference.offsetTop

export function trackCurrentPage() {
    const cPage = window.location.href;
    let pages = navReference.getElementsByTagName("ul")[0];
    console.log(pages)
    pages = pages.getElementsByTagName("li")
    console.log(pages)
    for (let i = 0; i < pages.length; i++) {
        let val = pages[i].getElementsByTagName("a")[0]
        if (cPage.search(val.split(".")[0]) !== -1) {
            val.style.backgroundColor = "#2A2A2A"
            break
        }
    }
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
