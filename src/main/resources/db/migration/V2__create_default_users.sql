INSERT INTO public._user (id, username, email, password, role, update_date, create_date)
VALUES (1, 'admin', 'admin@example.com', '$2a$10$l9v5L7xAF5Lec3zYC.vhceyltVa9gvXcQyeVxWghijWdwhiZukhGq', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;

INSERT INTO public._user (id, username, email, password, role, update_date, create_date)
VALUES (2, 'user', 'user@example.com', '$2y$10$HjIwhDNzDIzhN1zcHUuIjewbmrADZMEzGSkMM3KGUhXwW8gU3nFBi', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;