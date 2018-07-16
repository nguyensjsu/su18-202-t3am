(function(){
  var API_URL_GET_CARDS = 'http://localhost:8202/api/v1/cards';
  var API_URL_GET_USER_PROFILE = 'http://localhost:8202/api/v1/user_profile';
  var API_URL_GET_PURCHASES = 'http://localhost:8202/api/v1/purchases';
  var API_URL_USER_SIGNUP = 'http://localhost:8202/api/v1/signup';
  var API_URL_USER_SIGNIN = 'http://localhost:8202/api/v1/signin';

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
    const email = $('#txtEmail:visible').val().trim();
    const password = $('#txtPassword:visible').val().trim();

    localStorage['my_secure_token'] = '2c60158e-d432-4b78-a300-360cc6fa7260';
    localStorage['my_user_id'] = '2c60158e-d432-4b78-a300-360cc6fa7260';

    _onLoggedIn();
  }

  window.signup = function(){
    const email = $('#form-signup #txtEmail').val().trim();
    const password = $('#form-signup #txtPassword').val().trim();
    const full_name = $('#form-signup #txtName').val().trim();


    promiseAjax = new Promise((resolve, reject) => {
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
      )
        .catch(reject)
        .then(resolve);
    });


    promiseAjax.then((resp) =>{
      const {
        user_id,
        balance,
        full_name,
        date_added,
      } = resp;

      if(!user_id) {
        throw resp;
      }

      localStorage['my_secure_token'] = user_id;
      localStorage['my_user_id'] = user_id;

      _onLoggedIn(resp);
    }).catch((err) => {
      alert(`
        Cannot create your account because

        ${err}
      `)
    });
  }

  window.doOpenNewCard = _doOpenNewCard;
  window.doOpenNewPurchase = _doOpenNewPurchase;

  window.refresh = function(){
    _loadCards();
    _loadPurchase();
  }

  // events...
  function _onLoggedIn(auth_resp) {
    data.user = auth_resp;

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
        `${API_URL_GET_USER_PROFILE}?uid=${localStorage['my_user_id']}`,
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
        .then(resolve)
        .catch(reject)
    })
  }


  function _doOpenNewCard(){
    alert('add new card')
  }

  function _doNewCard(req){

  }

  function _doOpenNewPurchase(){
    alert('add new purchase')
  }

  function _doNewPurchase(req){

  }


  function _loadCards() {
    var promiseAjax;
    var listEl = '#card-list';
    var url = `${API_URL_GET_CARDS}?uid=${localStorage['my_user_id']}`;
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

    $(listEl).empty();

    promiseAjax.then(function(resp){
      if(resp.length === 0){
        throw '';
      }

      // [{"number":"123456789","code":"321","balance":20.0,"date_added":1524958728440,"uid":"2c60158e-d432-4b78-a300-360cc6fa7260"}]

      resp.forEach(function(item){
        $(`
          <tr>
            <td>${item.number}</td>
            <td>${item.code}</td>
            <td>${item.balance}</td>
            <td>${moment(item.date_added).format(formatter_date_full)}</td>
          </tr>
          `).appendTo(listEl)
      })
    }).catch(() => {
      $(`
        <tr>
          <td colspan="4">No Result</td>
        </tr>
        `).appendTo(listEl)
    });
  }

  function _loadPurchase() {
    var promiseAjax;
    var listEl = '#purchase-list';
    var url = `${API_URL_GET_PURCHASES}?uid=${localStorage['my_user_id']}`;
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

    $(listEl).empty();

    promiseAjax.then(function(resp){
      if(resp.length === 0){
        throw '';
      }

      // [{"number":"123456789","code":"321","balance":20.0,"date_added":1524958728440,"uid":"2c60158e-d432-4b78-a300-360cc6fa7260"}]

      resp.forEach(function(item){
        $(`
          <tr>
            <td>${item.number}</td>
            <td>${item.code}</td>
            <td>${item.balance}</td>
            <td>${moment(item.date_added).format(formatter_date_full)}</td>
          </tr>
          `).appendTo(listEl)
      })
    }).catch(() => {
      $(`
        <tr>
          <td colspan="4">No Result</td>
        </tr>
        `).appendTo(listEl)
    });
  }
})()
