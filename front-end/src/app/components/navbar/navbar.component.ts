import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Roles } from 'src/app/constant/role.constant';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent {
  imgUrl = '';
  roleCode: string | undefined = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    const profile = this.authService.getProfile();
    if (profile?.photoId) {
      this.imgUrl = `http://localhost:8080/files/${profile.photoId}`;
    } else {
      this.imgUrl = 'http://localhost:8080/files/1';
    }
    this.roleCode = profile?.roleCode;
  }

  onLogout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/login');
  }

  get isAdmin() {
    return Roles.SUPER_ADMIN === this.roleCode;
  }

  get isStudent() {
    return Roles.STUDENT === this.roleCode;
  }

  // get isReviewer() {
  //   return Roles.REVIEWER === this.roleCode;
  // }

  // get isAdmin() {
  //   return Roles.SUPER_ADMIN === this.roleCode;
  // }

  // get isCandidate() {
  //   return Roles.CANDIDATE === this.roleCode;
  // }
}
