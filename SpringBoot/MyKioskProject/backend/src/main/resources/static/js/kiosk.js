let cart = [];
let currentCategoryId = null;

$(document).ready(function () {
    loadCategories();
    applyUserBackground();
});

function applyUserBackground() {
    $.get('/api/auth/me', function (user) {
        if (user.backgroundImage) {
            $('.menu-section').css('background-image', `url('${user.backgroundImage}')`);
        }

        // Show menu button for all logged-in members (owners)
        $('#menu-btn').show();

        if (user.role === 'ADMIN') {
            $('#admin-btn').show();
        }
    }).fail(function () {
        console.log('Not logged in or error fetching user info');
    });
}

// Update category button class to 'tab-btn'
function loadCategories() {
    $.get('/api/categories', function (categories) {
        const nav = $('#category-nav');
        nav.empty();

        // Add "All" category at the beginning
        const allBtn = $(`<button class="tab-btn">전체</button>`);
        allBtn.click(function () {
            $('.tab-btn').removeClass('active');
            $(this).addClass('active');
            loadMenus(0);
        });
        nav.append(allBtn);

        categories.forEach((cat) => {
            const btn = $(`<button class="tab-btn">${cat.name}</button>`);

            btn.click(function () {
                $('.tab-btn').removeClass('active');
                $(this).addClass('active');
                loadMenus(cat.id);
            });
            nav.append(btn);
        });

        // Set "All" as active initially
        allBtn.click();
    });
}

function loadMenus(categoryId) {
    currentCategoryId = categoryId;
    let url = '/api/menus';
    if (categoryId && categoryId !== 0) {
        url += `?categoryId=${categoryId}`;
    }

    $.get(url, function (menus) {
        const grid = $('#menu-grid');
        grid.empty();
        menus.forEach(menu => {
            const item = $(`
                <div class="menu-item" onclick="addToCart(${menu.id}, '${menu.name}', ${menu.price})">
                    <div class="menu-img" style="background-image: url('${menu.imageUrl || '/images/default.png'}')"></div>
                    <div class="menu-info">
                        <div class="menu-name">${menu.name}</div>
                        <div class="menu-price">${menu.price.toLocaleString()}원</div>
                    </div>
                </div>
            `);
            grid.append(item);
        });
    });
}

function addToCart(id, name, price) {
    const existing = cart.find(i => i.menuId === id);
    if (existing) {
        existing.quantity++;
    } else {
        cart.push({ menuId: id, name: name, price: price, quantity: 1 });
    }
    renderCart();
}

function renderCart() {
    const list = $('#cart-items');
    list.empty();
    let total = 0;

    cart.forEach(item => {
        total += item.price * item.quantity;
        const row = $(`
            <div class="cart-item">
                <div class="cart-name">${item.name}</div>
                <div class="cart-right">
                    <span class="cart-price">${item.price.toLocaleString()}원</span>
                    <div class="qty-controls">
                        <button class="qty-btn" onclick="updateQty(${item.menuId}, -1)">-</button>
                        <span class="qty-val">${item.quantity}</span>
                        <button class="qty-btn" onclick="updateQty(${item.menuId}, 1)">+</button>
                    </div>
                </div>
            </div>
        `);
        list.append(row);
    });

    $('#total-amount').text(total.toLocaleString() + '원');
}

function updateQty(id, delta) {
    const item = cart.find(i => i.menuId === id);
    if (item) {
        item.quantity += delta;
        if (item.quantity <= 0) {
            cart = cart.filter(i => i.menuId !== id);
        }
        renderCart();
    }
}

// Custom Modal Helpers
function showConfirm(msg, onYes) {
    $('#confirm-msg').text(msg);
    $('#confirm-modal').css('display', 'flex');
    $('#confirm-yes-btn').off('click').on('click', function () {
        onYes();
        closeConfirmModal();
    });
}

function closeConfirmModal() {
    $('#confirm-modal').hide();
}

function showMessage(msg) {
    $('#message-text').text(msg);
    $('#message-modal').css('display', 'flex');
}

function closeMessageModal() {
    $('#message-modal').hide();
}

function cancelAll() {
    if (cart.length === 0) {
        return;
    }
    showConfirm('장바구니를 비우시겠습니까?', function () {
        cart = [];
        renderCart();
    });
}

function openPaymentModal() {
    if (cart.length === 0) {
        showMessage('장바구니가 비어있습니다.');
        return;
    }
    const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);
    $('#payment-amount').text(total.toLocaleString() + '원');
    $('#payment-modal').css('display', 'flex');
    $('#payment-progress').css('width', '0%');
    $('#payment-status-text').text('결제 준비 중...');

    // Virtual Payment Simulation
    setTimeout(() => {
        $('#payment-progress').css('transition', 'width 3s').css('width', '100%');
        $('#payment-status-text').text('카드 읽는 중...');

        setTimeout(() => {
            $('#payment-status-text').text('결제 승인 중...');
            submitOrder(total);
        }, 3000);
    }, 500);
}

function closePaymentModal() {
    $('#payment-modal').hide();
    $('#payment-progress').css('transition', 'none').css('width', '0%');
}

function submitOrder(totalAmount) {
    const data = {
        items: cart.map(i => ({ menuId: i.menuId, quantity: i.quantity, price: i.price })),
        totalAmount: totalAmount
    };

    $.ajax({
        url: '/api/orders',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            // alert('결제가 완료되었습니다!'); 
            // Instead of alert, show message and clear
            cart = [];
            renderCart();
            closePaymentModal();
            showMessage('결제가 완료되었습니다!');
        },
        error: function (err) {
            console.error(err);
            closePaymentModal();
            showMessage('결제 처리 중 오류가 발생했습니다.');
        }
    });
}

function toggleFullscreen() {
    if (!document.fullscreenElement) {
        document.documentElement.requestFullscreen().catch(err => {
            console.log(`Error attempting to enable full-screen mode: ${err.message} (${err.name})`);
        });
    } else {
        if (document.exitFullscreen) {
            document.exitFullscreen();
        }
    }
}

function logout() {
    location.href = '/api/auth/logout';
}
