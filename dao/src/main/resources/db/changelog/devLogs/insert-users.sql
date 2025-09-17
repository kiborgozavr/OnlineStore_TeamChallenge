insert into users(email, password, full_name, phone_number, role, created_at, refresh_token_key)
values
-- Admin test
('test_admin@gmail.com', '$2a$10$ETfgKekfb.1hqu6rxNxJx.77K0le84xqfSsxq6durC5Sg5/QS1Hx2', 'Admin Admin', '0505005050', 'ROLE_ADMIN', '2025-05-04T00:00:00', 'qRvH+vZKnfVYfL3S7tdcZT67vKyb6JkAYVn7r3z1Ufw='),

-- User test
('test_user@gmail.com', '$2a$10$0GoHTUsAQFEqPzPww8BqOuO5D2LfxvO8VP1edEbu88/i/7//xM2mC', 'John Doe', '0999999999', 'ROLE_USER', '2025-05-23T14:30:00', '3kR3z2BtLrpSfx3w2zCGfUpiyGL1tzzNqVe8DgqpMzo='),

-- Additional users
('alice.smith@gmail.com', '$2a$10$ctZ/omPP3ZX8uziOLqOvQeWbWLGCp9D1PTJ6Rm2N9zFrG4E/KDZxe', 'Alice Smith', '0911111111', 'ROLE_USER', '2025-05-22T10:15:00', 'upH9uNKcH0ICnOETh0Ojf3z02knS9WxL3Ue+xZ8tQeI='),
('bob.johnson@gmail.com', '$2a$10$la7es0Keg/AiRDBEemXRLumRdWgKvGNZ1AfO/ule.GuZxZn.k/PkC', 'Bob Johnson', '0922222222', 'ROLE_USER', '2025-05-22T11:00:00', 'Tc2+1FlwXZScDCm8OjjccsS4xU/9LDtsC5wz7E2Sg3I='),
('carol.white@gmail.com', '$2a$10$1rfK9/jHs./roaTNXDc1G.cCqGsaV//4ksWLQ02M004KEzYm1QeKW', 'Carol White', '0933333333', 'ROLE_USER', '2025-05-22T11:45:00', '7ub0LrbtX5xZMmQu3RtnT1TCugpUbybFZ4LdrhfMnaY='),
('david.brown@gmail.com', '$2a$10$4BQTNyGyNOOKpfd5ETEmQOZpKlw7Z7dOnFSlGfEOAaL9Zn5dKFhkK', 'David Brown', '0944444444', 'ROLE_USER', '2025-05-22T12:30:00', 'qT5zM++D/JzAF5zkRtAWlhcFxBb4FYBGh2AOCu0XPI8='),
('emily.davis@gmail.com', '$2a$10$EJM/h742g15bfgIOXuGhauHreen07B0YC.gitJfeGta1RVgoIA8FS', 'Emily Davis', '0955555555', 'ROLE_USER', '2025-05-22T13:15:00', 'hDDE1dA9D0O0LgG2NvC3DXbyzndgWKwFdd9RQ4KknBk='),
('frank.miller@gmail.com', '$2a$10$eFg418nJ/yQtPgixo7vf5OJT6H7w9cBI84tWZQM3/upae9.D9lFnW', 'Frank Miller', '0966666666', 'ROLE_USER', '2025-05-22T14:00:00', 'YDDhBiL6ZwnN+Kq7ZSoLpqmb7KvkeYd5FzC6RJf7kLk='),
('grace.wilson@gmail.com', '$2a$10$voEn3r.2/vSw0kLcjDnTjOqdqp42qZeEpDbWdiKLerEmJ/Zc6/UpS', 'Grace Wilson', '0977777777', 'ROLE_USER', '2025-05-22T14:45:00', 'w+lmnpR9E5WqzYqFAVOaWi6+R0x4UQwIq9FJW6z4NcE='),

-- More users
('hannah.king@gmail.com', '$2a$10$Qq2TJ7gRCKqWzleBq7Y1POsMd3K1xR7e.P5NmL9eXlG91x4kZkVcy', 'Hannah King', '0988888888', 'ROLE_USER', '2025-05-22T15:30:00', 'AKkFlbMGaf+BXxpnElIj3rmyEB3VV65D4+o+ZoH8guE='),
('ian.moore@gmail.com', '$2a$10$xG8Lz0fdzj8yVO9hr8nxVex8n.vmrH9yOZECwFCdA4TnFx0C2DAzC', 'Ian Moore', '0987878787', 'ROLE_USER', '2025-05-22T16:15:00', 'WZ/F6N8btxK9zoLxGpNPkeLMGiVVa0G4IBn1S9ewSCg='),
('julia.scott@gmail.com', '$2a$10$z9I0JqxkMZuwUe3Auv8/Ou2Zmu7BgB9G3c5yMZXZJgYdRxrf3YbBu', 'Julia Scott', '0986868686', 'ROLE_USER', '2025-05-22T17:00:00', 'TZqboU6pQaBfZTKx4nH5h3wPYdNE1SIPG4Jh3M0pjsk='),
('kevin.hall@gmail.com', '$2a$10$OB8oXH7kQmu6RJYu4QG5GecIfDdRpCkUq/SM/jzVOVrcy5YRR2uLO', 'Kevin Hall', '0985858585', 'ROLE_USER', '2025-05-22T17:45:00', '1I6Y7AoAVV7EGjv4PbqUrxwgbwTWfuqY4YOTn7u4JpY='),
('lisa.green@gmail.com', '$2a$10$X2Ra12n8LL2B4A75U6nDdO/G/rA5QZqNYRXuzy1cMoyY0smtth32i', 'Lisa Green', '0984848484', 'ROLE_USER', '2025-05-22T18:30:00', '8cN4q03HZq4EeqR9Slr2u5vvqhiYERLHL+9Z4BuY1x8='),
('mike.wright@gmail.com', '$2a$10$M3b5g0VqRzKBPXQs7kA9eOc0kRdfjzCm/DAmLmHdc9rdo84Ee9GgK', 'Mike Wright', '0983838383', 'ROLE_USER', '2025-05-22T19:15:00', '1Wa9TGElCNW4ptg6uHbPZrql2pK7T8ozBvds6yhtOJo='),
('nina.adams@gmail.com', '$2a$10$P0YHj0Z1fHdJg8yMZkWJkeA9gRyNfLkXw7vEXE2jF4uP0zA8RkVmS', 'Nina Adams', '0982828282', 'ROLE_USER', '2025-05-22T20:00:00', 'IMn5GVhfMEu3YtpydWyHiKlyHkz2d51SGvEttrKONZ4='),
('oliver.lee@gmail.com', '$2a$10$Rf92GeRmuPbXtZ78pjwPweAgRPu4c8zM6E9vFnNOMccy9/jdxZ8yK', 'Oliver Lee', '0981818181', 'ROLE_USER', '2025-05-22T20:45:00', 'kpvWx0bL1bD9Vhql3o5kBo8wFPmHLF0Akw2FzGGnqvQ='),
('paula.turner@gmail.com', '$2a$10$y3dZ/YPz8nlUlmKekf6QO.bUO7EFz5yREMLsPkmKw7VIlR2YBFOeq', 'Paula Turner', '0980808080', 'ROLE_USER', '2025-05-22T21:30:00', '4vFKAzjHBYT3T5mWEN+SrfavDAyJxjP6Yje8ruGrYzQ='),
('quentin.reed@gmail.com', '$2a$10$zphm3Ul9YcsmXt9EVffZGuScRcshKwWUtKMbVz4I/FaFV9P/xoL1q', 'Quentin Reed', '0979797979', 'ROLE_USER', '2025-05-22T22:15:00', 'jZq14InJzRL4GRUlA3JP7PIohgSz5XhCHLtOLtZKYFQ='),
('rachel.barnes@gmail.com', '$2a$10$uS7pK5j9B7NjfJrWhP5aQODME/Tx.VTRvTQKx2xR/mCqEDO5UIGP6', 'Rachel Barnes', '0978787878', 'ROLE_USER', '2025-05-22T23:00:00', 'h+naTP1uK8iClGVLePzKIBfvZRc5C88GmATbr7hytfA=');
