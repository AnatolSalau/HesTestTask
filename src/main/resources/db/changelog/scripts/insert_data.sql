INSERT INTO users (user_id, name, password, role_type, created_at, updated_at)
VALUES /*1*/(nextval('users_id_seq'), 'admin1', '$2a$12$C.UU1dcWUOnH1owgb8Wa9uW.mQcl9rdlFE7IJPUyNAxRhyMvL/Fw.', 'ROLE_ADMIN',
             '2024-01-29 00:00:31.412820', '2024-01-29 00:00:31.412820'),
    /*2*/(nextval('users_id_seq'), 'admin2', '$2a$12$JLDpzw26NDf8L1T93cWpLeRRK1efqOnCQYvxWi18quOC6oRi98xbe', 'ROLE_ADMIN',
          '2024-02-29 00:00:32.412820', '2024-01-29 00:00:32.412820'),
    /*3*/(nextval('users_id_seq'), 'user3', '$2a$12$UzEBbUqzoJwOnL/2Eq4N8.JAt.SlEVjhbix.cd5dBeCMwUKEGllUG', 'ROLE_OWNER',
          '2024-03-29 00:00:33.412820', '2024-01-29 00:00:33.412820'),
    /*4*/(nextval('users_id_seq'), 'user4', '$2a$12$Ja8J0554lUFMO5ZcwgJBbupWHF8Y4/ZNrmqfLkFUSNA4L/URZiAFC', 'ROLE_OWNER',
          '2024-04-29 00:00:34.412820', '2024-01-29 00:00:34.412820'),
    /*5*/(nextval('users_id_seq'), 'user5', '$2a$12$QVG1x14c5UBzalaLWaGR2e/PTwD/56R4gIzfQZWcjd6V1uh40sP7.', 'ROLE_OWNER',
          '2024-05-29 00:00:35.412820', '2024-01-29 00:00:35.412820');

INSERT INTO accounts (account_id, user_id, balance, is_blocked, currency_type, created_at, updated_at)
VALUES/*1*/(nextval('accounts_id_seq'), 3, 300.00, false, 'BYN', '2024-07-29 00:07:31.412821', '2024-07-29 00:07:31.412821'),
    /*2*/(nextval('accounts_id_seq'), 4, 400.00,false, 'EUR', '2024-08-29 00:08:32.412822', '2024-07-29 00:07:32.412822'),
    /*3*/(nextval('accounts_id_seq'), 5, 500.00,false, 'USD', '2024-09-29 00:09:33.412823', '2024-07-29 00:07:33.412823'),
    /*4*/(nextval('accounts_id_seq'), 3, 1000.00,false, 'BYN', '2024-07-29 00:07:34.412824', '2024-07-29 00:07:34.412824'),
    /*5*/(nextval('accounts_id_seq'), 4, 1000.00,false, 'EUR', '2024-08-29 00:08:35.412825', '2024-07-29 00:07:35.412825'),
    /*6*/(nextval('accounts_id_seq'), 5, 1000.00,false, 'USD', '2024-09-29 00:09:36.412826', '2024-07-29 00:07:36.412826');

INSERT INTO transactions(transaction_id, account_id, amount, created_at, currency_type, transaction_type)
VALUES /*1*/(nextval('transactions_id_seq'), 1, 300.00, '2024-10-29 00:01:31.412821', 'BYN', 'DEPOSIT'),
    /*2*/(nextval('transactions_id_seq'), 2, 400.00, '2024-10-29 00:02:32.412822', 'EUR', 'DEPOSIT'),
    /*3*/(nextval('transactions_id_seq'), 3, 500.00, '2024-10-29 00:03:33.412823', 'USD', 'DEPOSIT'),
    /*4*/(nextval('transactions_id_seq'), 4, 500.00, '2024-10-29 00:01:34.412824', 'BYN', 'DEPOSIT'),
    /*5*/(nextval('transactions_id_seq'), 5, 500.00, '2024-10-29 00:02:35.412825', 'EUR', 'DEPOSIT'),
    /*6*/(nextval('transactions_id_seq'), 6, 500.00, '2024-10-29 00:03:36.412826', 'USD', 'DEPOSIT'),
    /*7*/(nextval('transactions_id_seq'), 4, 500.00, '2024-10-29 00:01:37.412824', 'BYN', 'DEPOSIT'),
    /*8*/(nextval('transactions_id_seq'), 5, 500.00, '2024-10-29 00:02:38.412828', 'EUR', 'DEPOSIT'),
    /*9*/(nextval('transactions_id_seq'), 6, 500.00, '2024-10-29 00:03:39.412829', 'USD', 'DEPOSIT');
