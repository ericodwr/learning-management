import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-button',
  template: `
    <button
      *ngIf="!loading"
      type="{{ btnType }}"
      class="btn {{ classBtn }}"
      (click)="clickBtn()"
    >
      {{ label }}
    </button>
    <button *ngIf="loading" class="btn btn-primary" type="button" disabled>
      <span class="spinner-grow spinner-grow-sm" aria-hidden="true"></span>
      <span role="status">Loading...</span>
    </button>
  `,
  imports: [CommonModule],
  standalone: true,
})
export class ButtonComponent {
  @Input() label = '';
  @Input() classBtn = '';
  @Input() btnType = '';
  @Input() loading = false;

  @Output() clickChange = new EventEmitter<void>();

  clickBtn() {
    this.clickChange.emit();
  }
}
