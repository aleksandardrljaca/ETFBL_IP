import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserService } from '../../services/user-service/user.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const userService = inject(UserService);
  const user = userService.getUser();

  // Preuzmi dozvoljene uloge iz podataka rute
  const allowedRoles: string[] = route.data?.['roles'] || [];

  if (allowedRoles.includes(user.role)) {
    return true;
  }

  router.navigate(['']);
  return false;
};

