// Đưa dữ liệu giả định về các suất chiếu phim
const movieSchedule = [
    { id: 1, title: 'Phim A', time: '10:00 AM' },
    { id: 2, title: 'Phim B', time: '2:00 PM' },
    { id: 3, title: 'Phim C', time: '7:00 PM' },
];

// Hiển thị danh sách suất chiếu
const displaySchedule = () => {
    const scheduleContainer = document.getElementById('schedule-container');
    scheduleContainer.innerHTML = '';

    movieSchedule.forEach(movie => {
        const movieElement = document.createElement('div');
        movieElement.classList.add('movie');
        movieElement.innerHTML = `
            <h2>${movie.title}</h2>
            <p>Thời gian: ${movie.time}</p>
            <button onclick="bookTicket(${movie.id})">Đặt vé</button>
        `;
        scheduleContainer.appendChild(movieElement);
    });
};

// Đặt vé
const bookTicket = movieId => {
    const selectedMovie = movieSchedule.find(movie => movie.id === movieId);
    alert(`Bạn đã đặt vé cho ${selectedMovie.title} lúc ${selectedMovie.time}`);
};

// Hiển thị danh sách suất chiếu khi trang web được tải
window.onload = displaySchedule;
// Hàm xử lý đặt vé
const submitForm = () => {
    const selectedMovieId = document.getElementById('movie').value;
    const selectedTime = document.getElementById('time').value;
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;

    // Kiểm tra các trường đã được điền đầy đủ hay chưa
    if (!selectedMovieId || !selectedTime || !name || !email) {
        alert('Vui lòng điền đầy đủ thông tin.');
        return;
    }

    // Gửi thông tin đặt vé đến backend (cần thêm phần xử lý này)
    // ...

    // Hiển thị thông báo thành công và làm mới form
    alert(`Bạn đã đặt vé cho phim ${selectedMovieId} lúc ${selectedTime}.`);
    document.getElementById('booking-form').reset();
};

// Hàm cập nhật danh sách phim và thời gian từ backend (cần thêm phần xử lý này)
const updateMovieSchedule = () => {
    // ...
};

// Hiển thị danh sách suất chiếu khi trang web được tải và cập nhật danh sách phim từ backend
window.onload = () => {
    displaySchedule();
    updateMovieSchedule();
};
