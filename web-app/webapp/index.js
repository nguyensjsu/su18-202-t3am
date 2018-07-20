var API_HOST = 'http://localhost:8202';

(function(){
  fetch('/env')
    .then(res => res.json())
    .then(res => {
      API_HOST = res.API_HOST;
    })
    .catch()
    .then(_appInit)
})()


function _appInit(){
  var API_URL_GET_CARDS = `${API_HOST}/api/v1/cards`;
  var API_URL_ADD_RELOAD_CARD = `${API_HOST}/api/v1/reload`;
  var API_URL_ADD_PURCHASE = `${API_HOST}/api/v1/purchase`;
  var API_URL_GET_USER_PROFILE = `${API_HOST}/api/v1/user_profile`;
  var API_URL_GET_PURCHASES = `${API_HOST}/api/v1/purchases`;
  var API_URL_USER_SIGNUP = `${API_HOST}/api/v1/signup`;
  var API_URL_USER_SIGNIN = `${API_HOST}/api/v1/signin`;

  console.log('API_HOST', API_HOST)

  var formatter_date_full = 'MMMM Do YYYY, h:mm:ss a';

  var data = {
    user: null,
    cards: [],
    purchases: [],
  }

  $( function() {
    $( ".tabs" ).tabs();
    _doLogIn().then(_onLoggedIn, _onLoggedOff);
  } );

  // exposed...
  window.logoff = function(){
    _onLoggedOff();
  }


  window.login = function(){
    const email = $('#form-signin .txtEmail').val().trim();
    const password = $('#form-signin .txtPassword').val().trim();

    _makeApiCall(
        API_URL_USER_SIGNIN,
        {
          method: "POST",
          body: JSON.stringify({
            email,
            password,
          })
        }
      ).then((resp) => {
        const {
          user_id,
          email
        } = resp;

        localStorage['my_secure_token'] = user_id;
        localStorage['my_user_id'] = user_id;
        localStorage['my_user_email'] = email;


        _onLoggedIn(resp);


        // clear the forms...
        $([
          '#form-signin .txtEmail',
          '#form-signin .txtPassword',
        ].join(', ')).val('');
      }).catch((err) => {
        alert(`
          Login Failed:

          ${err}
        `)
      }).then(() => {

      });
  }

  window.signup = function(){
    const email = $('#form-signup .txtEmail').val().trim();
    const password = $('#form-signup .txtPassword').val().trim();
    const full_name = $('#form-signup .txtName').val().trim();

    _makeApiCall(
        API_URL_USER_SIGNUP,
        {
          method: "POST",
          body: JSON.stringify({
            email,
            full_name,
            password,
          })
        }
      ).then((resp) =>{
      const {
        user_id,
        email
      } = resp;

      if(!user_id || !email) {
        throw resp;
      }

      localStorage['my_secure_token'] = user_id;
      localStorage['my_user_id'] = user_id;
      localStorage['my_user_email'] = email;

      _onLoggedIn(resp);
    }).catch((err) => {
      alert(`
        Cannot create your account because

        ${err}
      `)
    }).then(() => {
        // clear the forms...
        $([
          '#form-signup .txtEmail',
          '#form-signup .txtPassword',
          '#form-signup .txtName',
        ].join(', ')).val('');
      });
  }

  window.doOpenNewCard = _doOpenNewCard;
  window.doOpenNewPurchase = _doOpenNewPurchase;

  window.refresh = function(){
    let cardTotalBalance = 0;
    let purchaseTotalBalance = 0;


    _loadCards()
      .then((_cardTotalBalance) => {
        cardTotalBalance = _cardTotalBalance;
      })
      .then(_loadPurchase)
      .then((_purchaseTotalBalance) => {
        purchaseTotalBalance = _purchaseTotalBalance;
      })
      .then(() => {
        // calculate the current balance
        data.current_balance = cardTotalBalance - purchaseTotalBalance;

        $([
          '#form-user-info .txtCurrentBalance',
          // '#form-add-card',
          // '#form-add-purchase',
        ].join(', ')).val(
          _formatCurrency( data.current_balance )
        );
      })
  }

  // events...
  function _onLoggedIn(auth_resp) {
    data.user = auth_resp;

    $([
      '#form-user-info .txtUserID',
      '#form-add-card .txtUserID',
      '#form-add-purchase .txtUserID',
    ].join(', ')).val(data.user.user_id);

    $([
      '#form-user-info .txtEmail',
      '#form-add-card .txtEmail',
      '#form-add-purchase .txtEmail',
    ].join(', ')).val(data.user.email);


    $([
      '#form-user-info .txtName',
      // '#form-add-card',
      // '#form-add-purchase',
    ].join(', ')).val(data.user.full_name);


    // alert('Logging In');

    $('#page-login').hide();
    $('#page-authenticated').show();

    window.refresh();
  }

  function _onLoggedOff() {
    // alert('Logging out');

    $('#page-login').show();
    $('#page-authenticated').hide();

    // reset
    data = {
      user: null,
      cards: [],
      purchases: [],
    };


    delete localStorage['my_secure_token'];
    delete localStorage['my_user_id'];
    delete localStorage['my_user_email'];
  }


  // state
  function _isLoggedIn(){
    if(!localStorage['my_secure_token'] || !localStorage['my_user_id']){
      return false;
    }

    return true;
  }


  // methods
  function _doLogIn(){
    if(!_isLoggedIn()){
      return Promise.reject();
    }

    return new Promise((resolve, reject) => {
      _makeApiCall(
        `${API_URL_GET_USER_PROFILE}?uid=${localStorage['my_user_id']}&email=${localStorage['my_user_email']}`,
        {
          headers:{
            'Content-Type': 'application/json'
          }
        }
      )
        .catch(reject)
        .then(resolve);
    });
  }

  function _makeApiCall(a, b, c){
    b = Object.assign({
      mode: "cors",
      cache: "no-cache",
      // credentials: "include",
      headers: {
          "Content-Type": "application/json; charset=utf-8",
      },
      redirect: "follow",
      referrer: "no-referrer",
    }, b);

    return new Promise((resolve, reject) => {
      fetch(a, b, c)
        .then(res => res.text())
        .then(res => {
          try{
            return Promise.resolve(JSON.parse(res));
          } catch(e){
            return Promise.resolve(res);
          }
        })
        .then(res => {
          if(res.error){
            throw res.msg;
          }
          return Promise.resolve(res.response);
        })
        .then(resolve)
        .catch(reject)
    })
  }


  function _doOpenNewCard(){
    var dlg;

    $('#dialog-add-card').dialog({
      resizable: false,
      draggable: false,
      height: "auto",
      width: 600,
      modal: true,
      open: function(){
        // reset the form...
        dlg = this;

        $([
          '#form-add-card .txtCardId',
          '#form-add-card .txtCardCode',
          '#form-add-card .txtCardBalance',
        ].join(', ')).val('');
      },
      buttons: {
        "Save": function() {
          _doNewCard({
              uid: data.user.user_id,
              number: $('#form-add-card .txtCardId').val().trim(),
              code: $('#form-add-card .txtCardCode').val().trim(),
              balance: $('#form-add-card .txtCardBalance').val().trim(),
            })
            .then(function() {
              $( dlg ).dialog( "close" );
              window.refresh();
            })
            .catch(function(err){
              alert(err);
            })
        },
        "Cancel": function() {
          $( dlg ).dialog( "close" );
        }
      },
    });
  }

  function _doNewCard(req){
    return _makeApiCall(
      API_URL_ADD_RELOAD_CARD,
      {
        method: "POST",
        body: JSON.stringify(req)
      }
    );
  }

  function _doOpenNewPurchase(){
    var dlg;

    $('#dialog-add-purchase').dialog({
      resizable: false,
      draggable: false,
      height: "auto",
      width: 600,
      modal: true,
      open: function(){
        // reset the form...
        dlg = this;

        $([
          '#form-add-purchase .txtPurchaseAmount',
          '#form-add-purchase .txtPurchaseNote',
        ].join(', ')).val('');
      },
      buttons: {
        "Save": function() {
          _doNewPurchase({
              uid: data.user.user_id,
              balance: $('#form-add-purchase .txtPurchaseAmount').val().trim(),
              note: $('#form-add-purchase .txtPurchaseNote').val().trim(),
            })
            .then(function() {
              $( dlg ).dialog( "close" );
              window.refresh();
            })
            .catch(function(err){
              alert(err);
            })
        },
        "Cancel": function() {
          $( dlg ).dialog( "close" );
        }
      },
    });
  }

  function _doNewPurchase(req){
    return _makeApiCall(
      API_URL_ADD_PURCHASE,
      {
        method: "POST",
        body: JSON.stringify(req)
      }
    );
  }


  function _loadCards() {
    var promiseAjax;
    const listEl = '#card-list';
    const url = `${API_URL_GET_CARDS}?uid=${localStorage['my_user_id']}`;
    if(!_isLoggedIn()){
      promiseAjax = Promise.reject();
    } else {
      promiseAjax = new Promise((resolve, reject) => {
        _makeApiCall(
          url,
          {
            method: "GET",
          } // extra request params...
        )
          .catch(reject)
          .then(resolve);
      });
    }

    $(listEl).html(`
      <tr>
        <td colspan="4">Loading...</td>
      </tr>
    `);

    $('.btn-refresh').hide();

    let finalAmount = 0;
    return promiseAjax.then(function(resp){
      if(resp.length === 0){
        throw '';
      }

      // [{"number":"123456789","code":"321","balance":20.0,"date_added":1524958728440,"uid":"2c60158e-d432-4b78-a300-360cc6fa7260"}]
      $(listEl).empty();
      finalAmount = resp.reduce(function(finalAmount, item){
        $(`
          <tr>
            <td>${item.number}</td>
            <td>${item.code}</td>
            <td>${_formatCurrency(item.balance)}</td>
            <td>${moment(item.date_added).format(formatter_date_full)}</td>
          </tr>
          `).appendTo(listEl)

        return finalAmount + Math.abs( item.balance );
      }, 0)
    }).catch(() => {
      $(listEl).html(`
        <tr>
          <td colspan="4">No Result</td>
        </tr>
      `)

      return Promise.resolve(0);
    }).then(function(){
      $('.btn-refresh').show();
      return Promise.resolve(finalAmount);
    });
  }


  function _loadPurchase() {
    var promiseAjax;
    const listEl = '#purchase-list';
    const url = `${API_URL_GET_PURCHASES}?uid=${localStorage['my_user_id']}`;
    if(!_isLoggedIn()){
      promiseAjax = Promise.reject();
    } else {
      promiseAjax = new Promise((resolve, reject) => {
        _makeApiCall(
          url,
          {
            method: "GET",
          } // extra request params...
        )
          .catch(reject)
          .then(resolve);
      });
    }

    $(listEl).html(`
      <tr>
        <td colspan="4">Loading...</td>
      </tr>
    `);

    $('.btn-refresh').hide();

    let finalAmount = 0;
    return promiseAjax.then(function(resp){
      if(resp.length === 0){
        throw '';
      }

      // [{"number":"123456789","code":"321","balance":20.0,"date_added":1524958728440,"uid":"2c60158e-d432-4b78-a300-360cc6fa7260"}]

      $(listEl).empty();


      finalAmount = resp.reduce(function(finalAmount, item){
        $(`
          <tr>
            <td>${_formatCurrency(item.balance)}</td>
            <td>${item.note || ''}</td>
            <td>${moment(item.date_added).format(formatter_date_full)}</td>
          </tr>
          `).appendTo(listEl)

        return finalAmount + Math.abs( item.balance );
      }, 0)
    }).catch(() => {
      $(listEl).html(`
        <tr>
          <td colspan="4">No Result</td>
        </tr>
      `)

      return Promise.resolve(0);
    }).then(function(){
      $('.btn-refresh').show();
      return Promise.resolve(finalAmount);
    });
  }



  function _formatCurrency(bal){
    return `$${bal.toFixed(2)}`
  }


  // validator for number...
  $('[type=number]').attr(
    'oninput',
    'window.validatorNumberInput(this)'
  );

  window.validatorNumberInput = (e) => {
    if (e.value.length > e.maxLength){
      e.value = e.value.slice(0, e.maxLength);
    }
  }
}
