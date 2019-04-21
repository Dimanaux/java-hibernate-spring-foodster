const logout = () => {
    $.ajax({
        url: '/logout',
        type: 'POST',
        success: (date) => {
            window.location.href = '/auth'
        }
    });
};
