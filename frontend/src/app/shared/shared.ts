// src/app/shared/shared.ts
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Exemplos de módulos PrimeNG que você provavelmente vai usar
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { DatePickerModule } from 'primeng/datepicker';



import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { DialogModule } from 'primeng/dialog';

export const SHARED_MODULES = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  TableModule,
  ButtonModule,
  InputTextModule,
  SelectModule,
  DatePickerModule,
  ConfirmDialogModule,
  ToastModule,
  DialogModule,
];
