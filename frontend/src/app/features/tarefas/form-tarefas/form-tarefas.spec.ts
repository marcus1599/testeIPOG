import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTarefas } from './form-tarefas';

describe('FormTarefas', () => {
  let component: FormTarefas;
  let fixture: ComponentFixture<FormTarefas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormTarefas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormTarefas);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
