console.log("Script loded");
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
  changeTheme();
});

// todo:
function changeTheme() {
  // set to web  page
  changePageTheme(currentTheme, currentTheme);

  // set the listener to change theme button
  const changeThemeButton = document.querySelector("#theme_change_button");

  changeThemeButton.addEventListener("click", (e) => {
    let oldTheme = currentTheme;
    console.log("change theme button clicked");

    if (currentTheme === "dark") {
      currentTheme = "light";
    } else {
      currentTheme = "dark";
    }
    changePageTheme(currentTheme, oldTheme);
  });
}

// set theme to localstorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// get theme from localStorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}
function changePageTheme(theme, oldTheme) {
  setTheme(currentTheme);
  document.querySelector("html").classList.remove(oldTheme);
  document.querySelector("html").classList.add(theme);

  //   change the text of button
  document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}
