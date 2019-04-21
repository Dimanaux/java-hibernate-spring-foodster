const appendComment = (comment) => {
    let commentList = $('#comments-list');
    commentList.append(
        `<div class="comment">
            <h4>${comment.author.username}</h4>
            <p>${comment.text}</p>
            <small>${comment.date}</small>
        </div>`
    );
};

const sendComment = () => {
    let text = $('#comment-text').val();
    $.ajax({
        url: window.location.pathname + '/comments',
        type: 'POST',
        data: {
            text: text
        },
        success: (data) => {
            appendComment(data);
        }
    });
};

$(document).ready(() => {
    $.ajax({
        url: window.location.pathname + '/comments',
        type: 'GET',
        success: (data) => {
            for (let comment of data) {
                appendComment(comment);
            }
        }
    });
});
