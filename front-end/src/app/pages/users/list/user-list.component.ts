import { Component, OnInit } from '@angular/core';
import { UsersResDto } from '../../../dto/user/users.res.dto';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html',
})
export class UserListComponent implements OnInit {
  constructor(private usersService: UsersService) {}

  users: UsersResDto[] = [];

  ngOnInit(): void {
    this.usersService.getAllUsers().subscribe((res) => {
      this.users = res;
    });
  }
}
