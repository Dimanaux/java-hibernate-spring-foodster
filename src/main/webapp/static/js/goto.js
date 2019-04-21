let ul = document.getElementById('sidemenu');

ul.addEventListener('click', (e) => {
    if (e.target.tagName === 'LI'){
      window.location.replace(e.target.children[0].href);
    }
});
