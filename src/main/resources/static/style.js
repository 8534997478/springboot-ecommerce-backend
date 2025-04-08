function toggleMenu() {
    const navLinks = document.querySelector(".nav-links");
    navLinks.classList.toggle("active");
}
function showLoginAlert() {
    alert("Please log in to continue with your purchase.");
    return false; // Prevents immediate redirection
}